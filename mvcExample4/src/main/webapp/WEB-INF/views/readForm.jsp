<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>번호</th>
		<td>${article.aid}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${article.name}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${article.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${content.content}</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="list">[목록]</a>
			<a href="update?aid=${article.aid}">[게시글 수정]</a>
			<a href="delete?aid=${article.aid}">[게시글 삭제]</a>		
		</td>
	</tr>
</table>
</body>
</html>