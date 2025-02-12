<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Inicio de Sesión</title>
	</head>

	<body>
		<form action="ServletController" method="post">
			<fieldset>
				<legend>
					<h1 style="color: green">MERCHANDISHING GF</h1>
				</legend>
				<table>
					<tr>
						<td><label>Nombre:</label></td>
						<td><input type="text" name="usuario"></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="contraseña"></td>
					</tr>
					<tr>
						<td><label>Perfil:</label></td>
						<td><select>
								<option>Administrador</option>
							</select></td>
					</tr>
					<tr>
						<td><input type="submit" name="action" value="login"></td>
						<td><input type="submit" name="action" value="Salir"></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</body>

	</html>