<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.yu.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>회원 목록 리스트</h2>
	<hr>
	<a href="insertReq.do">회원 추가</a>
	<table border="1">
		<tr>
			<th>아이디</th> <th>이름</th> <th>비밀번호</th> <th>이메일</th> <th>가입일</th>
		</tr>
		<%
			ArrayList<MemberVO> memberList = (ArrayList<MemberVO>) request.getAttribute("memberList");
		
			if(memberList != null)
				for(MemberVO member : memberList) {
		%>
		<tr>
			<td><a href="updateReq.do?id=<%=member.getId()%>"><%=member.getId() %></a></td>
			<td><%=member.getName() %></td>
			<td><%=member.getPassword() %></td>
			<td><%=member.getEmail() %></td>
			<td><%=member.getRegdate() %></td>
		</tr>
		<% } %>
	</table>
</div>
</body>
</html>