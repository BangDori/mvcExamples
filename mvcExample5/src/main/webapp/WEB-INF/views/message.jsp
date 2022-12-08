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
<c:choose>
	<c:when test="${execute}">
		<h2>실행 완료</h2>
	</c:when>
	<c:otherwise>
		<h2>오류 발생</h2>
	</c:otherwise>
</c:choose>
<hr>
<c:if test="${errors.notfound}"><font color="red">오류: 등록되지 않은 사용자입니다.</font><br></c:if>
<c:if test="${errors.mismatch}"><font color="red">오류: 패스워드가 일치하지 않습니다.</font><br></c:if>
<c:if test="${errors.email}"><font color="red">오류: 이메일을 입력해주세요.</font><br></c:if>
<c:if test="${errors.password}"><font color="red">오류: 패스워드를 입력해주세요.</font><br></c:if>
<c:if test="${errors.auth}"><font color="red">오류: 수정할 권한이 없습니다.</font><br></c:if>
<c:if test="${exec.update}">실행: 정상적으로 수정되었습니다.</c:if>
<c:if test="${exec.delete}">실행: 정상적으로 삭제되었습니다.</c:if>
<p>
<c:choose>
	<c:when test="${error eq 'auth' or execute}">
		<a href="list">[목록]</a>
	</c:when>
	<c:otherwise>
		<a href="login">[로그인]</a>	
	</c:otherwise>
</c:choose>
</body>
</html>