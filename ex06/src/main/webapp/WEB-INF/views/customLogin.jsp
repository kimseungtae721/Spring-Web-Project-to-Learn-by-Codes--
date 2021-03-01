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

<h1>custom login page</h1>
<h2><c:out value="${error}"/></h2>
<h2><c:out value="${logout}"/></h2>

<form method="post" action="/login">
	<div>
		<input type="text" name="username" value="">
	</div>

	<div>
		<input type="password" name="password" value="">
	</div>

	<div>
		<input type="submit">
	</div>
	<!--  csrf 공격 방어를 위해 동적 생성 -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />

</form>

</body>
</html>