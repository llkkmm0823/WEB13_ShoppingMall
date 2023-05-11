package com.ezen.shop.controller.action.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.dao.ProductDao;
import com.ezen.shop.dto.ProductVO;

public class CategoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String kind = request.getParameter("kind");

		ProductDao pdao = ProductDao.getInstance();
		ArrayList<ProductVO> list = pdao.selectKindProductList(kind);

		request.setAttribute("ProductKindList", list);
		String url = "product/productkind.jsp";
		request.getRequestDispatcher(url).forward(request, response);


	}

}
