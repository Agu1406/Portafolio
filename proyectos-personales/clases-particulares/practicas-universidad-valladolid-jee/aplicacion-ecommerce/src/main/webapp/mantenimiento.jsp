<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="Modelos.Producto" %>
        <% // Obtener la lista de productos del contexto de la aplicación List<Producto> productos = (List<Producto>)
                application.getAttribute("productos");
                %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>Administración de Productos</title>
                </head>

                <body>
                    <h1>Administrar Productos</h1>
                    <form action="ServletController" method="post">
                        <table border="1">
                            <tr>
                                <th>Referencia</th>
                                <th>Descripción</th>
                                <th>Precio</th>
                                <th>Acciones</th>
                            </tr>
                            <% for (Producto producto : productos) { %>
                                <tr>
                                    <td>
                                        <%= producto.getReferencia() %>
                                    </td>
                                    <td><input type="text" name="descripcion" value="<%= producto.getDescripcion() %>">
                                    </td>
                                    <td><input type="number" name="precio" value="<%= producto.getPrecio() %>"></td>
                                    <td>
                                        <input type="hidden" name="referencia" value="<%= producto.getReferencia() %>">
                                        <input type="submit" name="action" value="Actualizar">
                                    </td>
                                </tr>
                                <% } %>
                        </table>
                    </form>
                    <form action="ServletController" method="post">
                        <input type="submit" value="Salir" formaction="index.jsp">
                    </form>
                </body>

                </html>