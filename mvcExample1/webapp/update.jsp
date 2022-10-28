<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.yu.*" %>
<%
	request.setCharacterEncoding("utf-8");
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>회원 정보 수정/삭제</h2>
	<hr>
	<form name="form1" action="update.do" method="post">
		<input type="hidden" name="id" value="<%=memberVO.getId() %>"/>
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="<%=memberVO.getName() %>"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" value="<%=memberVO.getPassword() %>"/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="<%=memberVO.getEmail() %>"/></td>
			</tr>
			<tr>
				<td>가입일</td>
				<td><input type="text" name="regdate" value="<%=memberVO.getRegdate() %>"/></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="수정"/>
					<input type="reset" value="취소"/>
					<input type="button" value="삭제" onClick="deleteClick()"/>
				</td>
			</tr>
		</table>
	</form>

	<script>
		function deleteClick() {
			const check = confirm("정말 삭제하시겠습니까?");
			
			if(check) {
				document.form1.action = "delete.do";
				document.form1.submit();
			} else {
				return;
			}
		}
	</script>
</div>
</body>
</html>