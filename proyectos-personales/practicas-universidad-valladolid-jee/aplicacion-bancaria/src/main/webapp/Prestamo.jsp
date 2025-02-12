<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Solicitud de Préstamo</h2>
	<form action="PrestamoServlet" method="post">
		<label>Cantidad:</label> <input type="number" name="cantidad" required /><br />
		<input type="submit" value="Solicitar Préstamo" />
	</form>
	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>

</body>
</html>