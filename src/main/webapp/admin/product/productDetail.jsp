<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
<h1>상품 상세 보기</h1>
<table id="list">
	<tr><th>상품분류</th><td colspan="5">${kind }</td></tr>
	<tr><th align="center">상품명</th><td colspan="5">${ProductVO.name }</td></tr>
	<tr><th>원가 [A]</th><td width="60">${ProductVO.price1}</td>
		<th>판매가 [B]</th><td width="60">${ProductVO.price2}</td>
		<th>[B-A]</th><td>${ProductVO.price3}</td></tr>
	<tr><th>상세설명</th><td colspan="5"><pre>${ProductVO.content}</pre></td></tr>
	<tr><th>상품이미지</th><td colspan="5" align="center">
		<img src="product_images/${ProductVO.image}" width="200pt"></td></tr>
</table>
<input class="btn" type="button" value="수정" onclick="go_mod('${ProductVO.pseq}');">
<input class="btn" type="button" value="목록" onclick="go_mov();">
</article>
<%@ include file="/admin/footer.jsp"%>
