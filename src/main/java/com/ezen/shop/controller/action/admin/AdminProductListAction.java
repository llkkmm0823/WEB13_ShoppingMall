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
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.utill.Paging;

public class AdminProductListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String url = "admin/product/productList.jsp";

		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if(avo==null) url="shop.do?command=admin";
		else {
			
			if(request.getParameter("changeMenu")!=null) {
			//  서브메뉴를 클릭할 때 저장된 세션값으로 값이 입력되어 메뉴에 옮겨가는 것을 막기 위한 코드
				session.removeAttribute("page");
				session.removeAttribute("key");
			}

			Paging paging = new Paging();
			paging.setDisplayRow(10);
			paging.setDisplayPage(10);

		if(request.getParameter("page")!=null) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
			//request에 전달된 파라미터중 page값이 있다면 세션에도 그 페이지값을 저장
			session.setAttribute("page",Integer.parseInt(request.getParameter("page")));
		}else if(session.getAttribute("page") != null){
			//이전에 혹시 세션에 페이지 값을 저장한 적이 있다면 
			paging.setPage((Integer)session.getAttribute("page"));
		}else {
			paging.setPage(1);
			//session.removeAttribute("page");
		}
			
			String key="";
			if(request.getParameter("key") != null ) {
				//검색할 단어가 같이 전달되었다면
				key=request.getParameter("key");
				session.setAttribute("key",key);
			}else if(session.getAttribute("key")!=null) {
				//전달된 key파라미터가 없다면 세션에 저장된 key값은 없는지 점검
				key=(String)session.getAttribute("key");
			}else {
				key="";
				session.removeAttribute("key");
			}
			
			AdminDao adao = AdminDao.getInstance();
			
			int count = adao.getAllcount("product","name",key);
			
			paging.setTotalCount(count);

			ArrayList<ProductVO> productList = adao.adminProductList(paging, key);
			request.setAttribute("productList", productList);
			request.setAttribute("paging", paging);

		}
	     request.getRequestDispatcher(url).forward(request, response);

	}
}
