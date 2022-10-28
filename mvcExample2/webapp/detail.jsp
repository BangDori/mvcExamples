<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="product.*" %>
<%
	request.setCharacterEncoding("utf-8");
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>상품 상세 페이지</h2>
	<hr>
	<table>
		<tr>
			<td>상품명:</td>
			<td><%=productVO.getName() %></td>
		</tr>
		<tr>
			<td>모델:</td>
			<td><%=productVO.getModel() %></td>
		</tr>
		<tr>
			<td>제조국:</td>
			<td><%=productVO.getMadein() %></td>
		</tr>
		<tr>
			<td>가격:</td>
			<td><%=productVO.getPrice() %></td>
		</tr>
	</table>
	<br>
	<a href="buy.do?id=<%=productVO.getId()%>">[상품 구매]</a>
	<a href="list.do">[목록 보기]</a>
</div>
</body>
</html>