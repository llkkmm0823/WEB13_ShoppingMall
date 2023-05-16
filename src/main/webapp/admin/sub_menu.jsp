<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<nav id="sub_menu">
<h1>Admin Setting</h1>
	<ul>
			<li><a href='shop.do?command=adminProductList&changeMenu=1'>상품리스트</a></li>
			<li><a href='shop.do?command=adminOrderList&changeMenu=1'>주문리스트</a></li>
			<li><a href='shop.do?command=adminMemberList&changeMenu=1'>회원리스트</a></li>
			<li><a href='shop.do?command=adminQnaList&changeMenu=1'>Q&amp;A리스트</a></li>
		</ul>
</nav>
<!-- 서브메뉴를 클릭할 때 저장된 세션값으로 값이 입력되어 메뉴에 옮겨가는 것을 막기 위해 changeMenu값을 1로 같이 전달
(command로 인해 action파일로 값이 전달되었을 때 changeMenu != null을 조건으로 한 if 문을 통해 세션값 초기화 예정)
 -->