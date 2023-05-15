package com.ezen.shop.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.QnaDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.QnaVO;

public class QnaViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "qna/qnaView.jsp";
		int qseq = Integer.parseInt(request.getParameter("qseq"));

		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
			if(mvo==null) {
				url="shop.do?command=loginForm";
			}else {
			QnaDao qdao = QnaDao.getInstance();
			QnaVO qvo = qdao.getQna(qseq);
			request.setAttribute("qnaVO", qvo);
			}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
