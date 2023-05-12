package com.ezen.shop.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.MemberVO;

public class OrderInsertOneAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달되는 상품번호, 수량, 로그인 유저의 아이디를 전달받아서
		// 주문테이블에 주문을 추가	OrderDao의 메서드 이름은 insertOrderOne();
		// 주문 추가 후 리턴된 oseq를 이용해서 shop.do?command=orderList&oseq=주문번호로 이동

		int pseq=Integer.parseInt(request.getParameter("pseq"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		//로그인 체크
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		String url="";
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			OrderDao odao = OrderDao.getInstance();
			//int oseq = odao.insertOrderOne(pseq,quantity,mvo.getId());
			odao.insertOrders(mvo.getId());
			int oseq=odao.lookupMaxOseq();
			odao.insertOrder_Detail(pseq,quantity,oseq);

			url="shop.do?command=orderList&oseq="+oseq;
		}
		response.sendRedirect(url);
	}

}
