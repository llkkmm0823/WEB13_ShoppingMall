<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="mypage/mypage.js"></script>
<script>
var a = confirm("장바구니로 이동하시겠습니까?");
if(a){
	location.href='shop.do?command=cartList';
} else {
	history.go(-1);
}
</script>