<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_image_menu.jsp"%>

<article>
<h2> My page(주문 상세 정보)</h2>
<form>

	<h3>주문자 정보</h3>
	<table id="cartList">
		<tr><th>주문일자</th><th>주문번호</th><th>주문자</th><th>주문총액</th></tr>
		<tr>
			<td><fmt:formatDate value="${orderDetail.indate }" type="date"/></td>
			<td>${orderDetail.oseq}</td>
			<td>${orderDetail.mname}</td>
			<td><fmt:formatNumber type="currency" value="${totalPrice }"/></td>
		</tr>
		<tr>
			<td colspan="4">
				<b>배송주소 : ${orderDetail.zip_num} ${orderDetail.address1} ${orderDetail.address2 }</b>
			</td>
		</tr>
	</table>
	
<h3>주문 상품 정보</h3>
	<table id="cartList">
		<tr><th>상품명</th><th>상품별주문번호</th><th>수량</th><th>가격</th><th>처리상태</th></tr>
		<c:forEach items="${orderList }" var="orderVO">
		<tr>
			<td>${orderVO.pname }</td><td>${orderVO.odseq}</td>
			<td>${orderVO.quantity}</td>
			<td><fmt:formatNumber type="currency" value="${orderVO.price2*orderVO.quantity }"/></td>
			<td>
				<c:choose>
					<c:when test='${orderVO.result=="1"}'>진행중</c:when>
					<c:otherwise><span style="color=red">처리완료</span></c:otherwise>
				</c:choose></td></tr>
		</c:forEach>
	</table>
	<div class="clear"></div>
	<div id="buttons" style="float:right">
		<input type="button" value="뒤로" class="cancel" onclick="history.go(-1)"/>
	</div>
</form>
</article>
<%@ include file="../footer.jsp"%>
