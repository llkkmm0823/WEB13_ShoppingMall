package com.ezen.shop.controller.action.member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.MemberDao;
import com.ezen.shop.dto.MemberVO;


public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = new MemberVO();


		mvo.setId(request.getParameter("id"));
		mvo.setName(request.getParameter("name"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress1(request.getParameter("address1"));
		mvo.setAddress2(request.getParameter("address2"));

		int result = mdao.insertMember( mvo );
		String message = "";

		if( result==1) message = "회원가입이 완료되었습니다. 로그인하세요.";
		else message = "회원가입이 실패했습니다. 재시도하세요.";

		response.sendRedirect( "shop.do?command=loginForm&message=" +URLEncoder.encode(message,"UTF-8"));
	}

}
