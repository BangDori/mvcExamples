<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.login==null}"><a href="login">[로그인]</a></c:if>
<c:if test="${sessionScope.login!=null}">
"${sessionScope.login.name}님 환영합니다."
<a href="write">[게시글 쓰기]</a>
<a href="logout">[로그아웃]</a>
</c:if>
<hr>
<table border="1">
	<tr>
		<th>번호</th> <th>제목</th> <th>작성자</th> <th>작성일</th> <th>최근 수정일</th> <th>조회수</th>
	</tr>
	<c:forEach var="article" items="${articles}">
		<tr>
			<td align="center">${article.aid}</td>
			<td><a href="read?aid=${article.aid}">${article.title}</a></td>
			<td>${article.name}</td>
			<td>${article.regdate}</td>
			<td>${article.moddate}</td>
			<td align="center">${article.readcnt}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>