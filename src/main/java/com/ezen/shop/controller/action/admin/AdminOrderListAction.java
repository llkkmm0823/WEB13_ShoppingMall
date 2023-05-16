package com.ezen.shop.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.AdminDao;
import com.ezen.shop.dto.AdminVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.utill.Paging;

public class AdminOrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "admin/order/orderList.jsp";

		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
		if (avo == null)
			url = "shop.do?command=admin";
		else {
			
			if(request.getParameter("changeMenu")!=null) { 
				//  서브메뉴를 클릭할 때 저장된 세션값으로 값이 입력되어 메뉴에 옮겨가는 것을 막기 위한 코드
				session.removeAttribute("page");
				session.removeAttribute("key");
			}

			Paging paging = new Paging();
			paging.setDisplayRow(10);
			paging.setDisplayPage(10);

			if (request.getParameter("page") != null) {
				paging.setPage(Integer.parseInt(request.getParameter("page")));
				session.setAttribute("page", Integer.parseInt(request.getParameter("page")));
			} else if (session.getAttribute("page") != null) {
				paging.setPage((Integer) session.getAttribute("page"));
			} else {
				paging.setPage(1);
			}

			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				key = "";
				session.removeAttribute("key");
			}

			AdminDao adao = AdminDao.getInstance();
			
			int count = adao.getAllcount("order_view","mname",key);
			
			paging.setTotalCount(count);
			
			ArrayList<OrderVO> orderList = adao.adminOrderList(paging, key);
			request.setAttribute("orderList", orderList);
			request.setAttribute("key", key);
			request.setAttribute("paging", paging);
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
