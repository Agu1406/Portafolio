<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cuentas</title>
</head>
<body>
	<form action="CuentaServlet" method="post">
		<label>ISBN:</label> <input type="text" name="ISBN" /><br /> <label>Titular:</label>
		<input type="text" name="usuario" /><br /> <label>Saldo:</label> <input
			type="text" name="saldo" /><br />
		<c:if test="${not empty error}">
			<p style="color: red;">${error}</p>
		</c:if>
		<input type="submit" value="Alta Cuenta" name="decision">
		<input type="submit" value="Prestamos" name="decision">
	</form>
</body>
</html>