<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, product.*" %>
<%
	request.setCharacterEncoding("utf-8");
	ArrayList<ProductVO> productList = (ArrayList<ProductVO>) request.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>상품 게시판</h2>
	<hr>
	<table border="1">
		<tr>
			<th>상품명</th> <th>모델</th> <th>조회수</th>
		</tr>
		<%
			if(productList != null)
				for(ProductVO product : productList) {
		%>
		<tr>
			<td><%=product.getName() %></td>
			<td><a href="detail.do?id=<%=product.getId()%>"><%=product.getModel() %></a></td>
			<td><%=product.getReadcnt() %></td>
		</tr>
		<% } %>
	</table>
	
	<br>
	<a href="buy.do">[장바구니]</a>
</div>
</body>
</html>