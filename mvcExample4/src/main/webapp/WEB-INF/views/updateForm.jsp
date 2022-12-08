<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateReq?aid=${article.aid}" method="post">
<h3>번호:</h3>
${article.aid}
<p>
<h3>제목:</h3>
<input type="text" name="title" value="${article.title}">
<p>
<h3>내용:</h3>
<textarea name="content" rows="10" cols="50" style="resize: none;">
${content.content }
</textarea>
<p>
<input type="submit" value="글 수정">
</form>
</body>
</html>