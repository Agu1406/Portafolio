<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acceso</title>
</head>
<body>
	<form action="ClienteServlet" method="post">
		<label>Nombre:</label> <input type="text" name="usuario" value="${sessionScope.cliente.usuario}"/><br />
        <label>Contraseña:</label> <input type="password" name="password"/><br />
        <input type="submit" value="Acceso"/>
	</form>
	<c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>