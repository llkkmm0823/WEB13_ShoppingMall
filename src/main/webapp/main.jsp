<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="header.jsp"%>

<!--매번 맨 위의 카테고리, 로그인정보등의 내용을 일일히 코드 및 복붙할 수 없으므로 따로 작성해두고 include하는 방식 -->

<!--메인 이미지 시작 : 각 기능별 페이지를 대표하는 이미지-->
<div id="main_img">
	<img src="images/main_img.jpg" style="border-radius:20px 20px 20px 20px; border:2px solid white;">
</div>


<!--신상품-->
<h2>New Item</h2>
<div id="bestProduct">
	<c:forEach items="${newList}" var="productVO" >
		<div id="item"> <!--상품 한 개, 한 칸 -->
			<a href="shop.do?command=productDetail&pseq=${productVO.pseq}">
				<img src="product_images/${productVO.image}"/>
				<h3>${productVO.name}
					- <fmt:formatNumber value="${productVO.price2}" type="currency"/></h3>		
			</a>
		</div>
	</c:forEach>
</div>
<div class="clear"></div>

<!--베스트 상품-->
<h2>Best Item</h2>
<div id="bestProduct">
	<c:forEach items="${bestList}" var="productVO" >
		<div id="item"> <!--상품 한 개, 한 칸 -->
			<a href="shop.do?command=productDetail&pseq=${productVO.pseq}">
				<img src="product_images/${productVO.image}"/>
				<h3>${productVO.name}
					- <fmt:formatNumber value="${productVO.price2}" type="currency"/></h3>
			</a>
		</div>
	</c:forEach>
</div>



<%@ include file ="footer.jsp"%>
<!--매번 맨 아래의 개인정보보호방침등의 내용을 일일히 코드 및 복붙할 수 없으므로 따로 작성해두고 include하는 방식 -->

