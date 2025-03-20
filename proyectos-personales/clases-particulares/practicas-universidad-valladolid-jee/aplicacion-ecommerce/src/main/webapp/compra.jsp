<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page import="Modelos.Producto" %>
		<% // Obtener las cookies de la solicitud Cookie[] cookies=request.getCookies(); String cesta=null; if (cookies
			!=null) { for (Cookie cookie : cookies) { if ("cesta".equals(cookie.getName())) { cesta=cookie.getValue(); }
			} } // Obtener la lista de productos del contexto de la aplicación List<Producto> productos = (List
			<Producto>) application.getAttribute("productos");
				%>
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<title>Cesta de Compra</title>
				</head>

				<body>
					<h1>Cesta de Compra</h1>
					<% // Verificar si la cesta está vacía if (cesta==null || cesta.isEmpty()) { %>
						<p>No ha seleccionado ningún artículo.</p>
						<% } else { String[] productosSeleccionados=cesta.split(","); double total=0; %>
							<table border="1">
								<tr>
									<th>Referencia</th>
									<th>Descripción</th>
									<th>Precio</th>
								</tr>
								<% for (String referencia : productosSeleccionados) { for (Producto producto :
									productos) { if (producto.getReferencia().equals(referencia)) { total
									+=producto.getPrecio(); %>
									<tr>
										<td>
											<%= producto.getReferencia() %>
										</td>
										<td>
											<%= producto.getDescripcion() %>
										</td>
										<td>$<%= producto.getPrecio() %>
										</td>
									</tr>
									<% } } } %>
							</table>
							<p>Total: $<%= total %>
							</p>
							<% } %>
								<form action="ServletController" method="post">
									<input type="hidden" name="action" value="finalizarCompra">
									<input type="submit" value="Finalizar Compra">
								</form>
				</body>

				</html>