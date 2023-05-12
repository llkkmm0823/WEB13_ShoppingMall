package com.ezen.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;

public class MypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url ="mypage/mypage.jsp";

		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			// 진행중인 주문내역
			// 현재 로그인한 사용자의 아직 배송되지 않은(처리진행중) 주문내역이 보여짐
			// ex)한 번에 2,3개의 상품 씩 3회에 걸쳐 주문한 상태(처리진행중) -> 진행중인 주문 내역은 세 줄로 표시됨(주문 건 별 표시)

			// 표시내용은 주문 건별 대표상품의 이름 활용하여 슬리퍼 포함 3건, 겨울용부츠 외 2건 등등 총 세 줄이 표시됨
			// 각 행에는 상세보기 버튼이 있어 클릭하면 그 주문에 속한 세개의 상품을 볼 수 있음

			//mypage.jsp에 최종 전달되어 화면에 보여질 리스트 생성
			ArrayList<OrderVO> finalList = new ArrayList<>();
			//(finalList.get(0).getName() -> "XXXX외 2건") 과 같이 ,list를 구성할 예정

			// 이를 위하여 가장 먼저 필요한 사항은 주문 번호들
			// order_view에서 주문자 아이디로 검색하고, result가 1인 주문들을 검색하여 주문 번호(oseq)들을 조회
			// 위의 예를 든 상태라면 주문번호는 다음과 같을 것
			// 22 22 22 24 24 26 27 27 27  <-  조회된 주문번호(oseq)

			// 정작 우리에게 필요한건 oseq으로 묶여진 22 24 26 27이므로 sql문에 distinct 키워드를 사용하여 조회
			// select distinct oseq from order_view where id=? and result='1'
			// select distinct : 중복제거 키워드 _ 조회하는데 중복은 제거하고 조회해와라

			OrderDao odao = OrderDao.getInstance();

			// 주문번호들의 리스트, 중복을 없앤 검색
			ArrayList<Integer> oseqList = odao.selectOseqOrderIng(mvo.getId());

			// 주문번호별로 상품을 다시 조회하여 세 개의 상품이 있다면 그 들 가격의 총 금액을 계산하고 첫번째 상품의 price2를 저장
			// 첫 번째 상품의 상품이름도 대표상품 이름 외 2건이라고 바꿈
			// 대표상품을 최종화면에 표시될 리스트(finalList)에 따로 담음

			// 22번 주문의 3개 상품에서 첫 번째 상품의 상품 이름을 "상품이름 외 2건" 으로 변경
			// 3개의 가격을 합산한 금액을 22번주문의 price2에 저장
			// 변경된 첫 번째 상품을 finalList에 저장

			for(Integer oseq : oseqList) {
				//주문번호로 상품 검색
				ArrayList<OrderVO> orderListByOseq = odao.listOrderByOseq(oseq);

				// 원본 보호를 위해 리턴된 리스트의 첫 번째 상품을 별도의 변수에 저장
				OrderVO firstProduct = orderListByOseq.get(0);

				// 총 결제 금액 계산
				int totalPrice=0;
				for(OrderVO ovo : orderListByOseq)
					totalPrice += ovo.getPrice2()*ovo.getQuantity();

				//상품명과 가격필드 변경
				firstProduct.setPrice2(totalPrice);
				firstProduct.setPname(firstProduct.getPname() + " 포함 " + orderListByOseq.size() + "건");

				// 모든 변경을 마친 firstProduct는 finalList에 저장
				finalList.add(firstProduct);
			}
			request.setAttribute("orderList",finalList);
			request.setAttribute("title","진행 중인 주문 내역");
			request.getRequestDispatcher(url).forward(request, response);

		}
	}
}
