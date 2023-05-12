package com.ezen.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.CartDao;
import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;

public class OrderInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {

			//주문자 아이디로 검색한 카트목록(지금 주문처리할) 목록을 먼저 조회 ( cdao생성필요)
			CartDao cdao = CartDao.getInstance();
			ArrayList<CartVO> list = cdao.selectCart(mvo.getId());

			//조회한 list와 주문자의 아이디를 갖고 OrderDao에 가서 오더와 오더 디테일에 데이터를 추가
			//주문추가 후 추가한 주문의 주문번호를 리턴받음
			OrderDao odao = OrderDao.getInstance();
			//int Oseq = odao.insertOrder(list,mvo.getId());

			//한번에 호출이 아닌 메서드로 나누어 호출하기

			//1. orders 테이블에 레코드 추가
			odao.insertOrders(mvo.getId());

			//2. orders테이블에 추가한 oseq조회
			int Oseq = odao.lookupMaxOseq();

			//3. order_detail 테이블에 레코드 추가
			odao.insertOrder_Detail(list,Oseq);

			//4. cart의 내용을 삭제
			odao.deleteCart(list);

			//방금 주문에 성공한 주문번호를 갖고 오더리스트로 이동하여 주문번호로 주문 내역을 다시 조회하고 jsp로 이동
			url="shop.do?command=orderList&oseq=" + Oseq;
		}
		response.sendRedirect(url);
	}

}
