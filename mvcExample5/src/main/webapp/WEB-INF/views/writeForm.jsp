<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="writeReq" method="post">
	<input type="hidden" name="writer" value="${sessionScope.login.id}"/>
	제목: <br>
	<input type="text" name="title">
	<p>
	내용: <br>
	<textarea name="content" rows="10" cols="50" style="resize: none;"></textarea>
	<p>
	<input type="hidden" name="readcnt" value="0">
	<input type="submit" value="새 글 등록">
</form>
</body>
</html>