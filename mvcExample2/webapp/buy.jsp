<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, product.*" %>
<%
	request.setCharacterEncoding("utf-8");
	ArrayList<ProductVO> basket = (ArrayList<ProductVO>) session.getAttribute("basket");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>장바구니 내용</h2>
	<hr>
	<a href="reset.do">[초기화]</a>
	<br><br>
	
	<%
		if(basket == null || basket.isEmpty())
			out.println("장바구니가 비었습니다. <br><br>");
		else
			for(ProductVO pt : basket) {
	%>
	모델: <%=pt.getModel() %> <br>
	가격: <%=pt.getPrice() %> <br> <br>
	<% } %>
	
	<a href="list.do">목록 보기</a>
</div>
</body>
</html>