<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>새로운 멤버 추가</h2>
	<br>
	<form action="add" method="post">
		<table border="1">
			<tr>
				<td>이름</td>
				<td> <input type="text" name="name"> </td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td> <input type="password" name="password"> </td>
			</tr>
			<tr>
				<td>이메일</td>
				<td> <input type="text" name="email"> </td>
			</tr>
			<tr>
				<td>가입일</td>
				<td> <input type="text" name="regdate"> </td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력"> &nbsp;&nbsp; 
					<a href="list">회원 리스트</a>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>