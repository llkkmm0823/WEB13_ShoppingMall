<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../header.jsp"%>
<%@ include file="sub_image_menu.html"%>

	<article>
		<h2> Item </h2>
		<c:forEach items="${ProductKindList}" var="ProductVO">
			<div id="item">
				<a href="shop.do?command=productDetail&pseq=${ProductVO.pseq}">
					<img src="product_images/${ProductVO.image}"/>
					<h3>${ProductVO.name}</h3>
					<p>${ProductVO.price2}</p>
				</a>
			</div>
		</c:forEach>
		<div class="clear"></div>
	</article>
	
<%@ include file ="../footer.jsp"%>