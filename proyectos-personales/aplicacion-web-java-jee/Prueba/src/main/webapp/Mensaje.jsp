<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Acceso.jsp" method="post">
		<c:if test="${not empty error}">
			<p style="color: red;">${error}</p>
		</c:if>
		<c:if test="${not empty mensaje}">
			<p style="color: red;">${mensaje}</p>
		</c:if>
		<input type="submit" name="Volver">
	</form>
</body>
</html>