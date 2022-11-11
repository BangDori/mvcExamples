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
	<h2>회원정보 수정/삭제</h2>
	<hr>
	<form name="form1" action="update" method="post">
		<input type="hidden" name="id" value="${member.id}"/>
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${member.name}" />
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" value="${member.password}" />
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${member.email}" />
			</tr>
			<tr>
				<td>가입일</td>
				<td><input type="text" name="regdate" value="${member.regdate}" />
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="수정" />
					<input type="reset" value="취소" />
					<input type="button" value="삭제" onClick="deleteClick(${member.id})" />
				</td>
			</tr>
		</table>
	</form>
</div>

<script>
	function deleteClick(id) {
		const check = confirm("정말 삭제하시겠습니까?");

		if(check) {
			document.form1.action ="delete/" + id;
			document.form1.submit();
		} else {
			return;
		}
	}
</script>
</body>
</html>