<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link rel="stylesheet" type="text/css" href="css/shop.css">
</head>
<body>
<%
	response.sendRedirect("shop.do?command=index");
%>
</body>
</html>