package CastillaLeon.Valladolid.IESGregorioFernandez.aplicacion_ecommerce.Controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.FilterChain;
import CastillaLeon.Valladolid.IESGregorioFernandez.aplicacion_ecommerce.Modelos.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Servlet que maneja las peticiones de la aplicación
@WebServlet("/CastillaLeon.Valladolid.IESGregorioFernandez.aplicacion_ecommerce.Controladores.ServletController")
public class ServletController extends HttpServlet implements ServletContextListener {

    // Método que se ejecuta al iniciar la aplicación
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Producto> productos = new ArrayList<>();
        // Agregar productos a la lista con referencia, descripción y precio
        productos.add(new Producto("REF001", "Artículo 1", 1000)); // Precio en enteros
        productos.add(new Producto("REF002", "Artículo 2", 1500));
        productos.add(new Producto("REF003", "Artículo 3", 2000));
        // Almacenar la lista de productos en el contexto de la aplicación
        sce.getServletContext().setAttribute("productos", productos);
    }

    // Método que maneja las peticiones POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Acción para ver la cesta de compra
        if ("verCesta".equals(action)) {
            String[] productosSeleccionados = request.getParameterValues("productos");
            if (productosSeleccionados == null || productosSeleccionados.length == 0) {
                request.setAttribute("mensaje", "No ha seleccionado ningún artículo.");
                request.getRequestDispatcher("compra.jsp").forward(request, response);
            } else {
                // Almacenar los productos seleccionados en una cookie
                Cookie cestaCookie = new Cookie("cesta", String.join(",", productosSeleccionados));
                cestaCookie.setMaxAge(60 * 60 * 24 * 365); // 1 año
                response.addCookie(cestaCookie);
                request.getRequestDispatcher("compra.jsp").forward(request, response);
            }
        }
        // Acción para iniciar sesión
        else if ("login".equals(action)) {
            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("contraseña");
            HttpSession session = request.getSession();

            if (usuario == null || usuario.isEmpty() || contraseña == null || contraseña.isEmpty()) {
                request.setAttribute("error", "El nombre y el password son obligatorios.");
                request.getRequestDispatcher("acceso.jsp").forward(request, response);
            } else if ("admin".equals(usuario) && "admin".equals(contraseña)) {
                session.setAttribute("usuario", usuario);
                request.getRequestDispatcher("mantenimiento.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Sesión nula por credenciales inválidas.");
                request.getRequestDispatcher("acceso.jsp").forward(request, response);
            }
        }
        // Acción para finalizar la compra
        else if ("finalizarCompra".equals(action)) {
            Cookie cestaCookie = new Cookie("cesta", null);
            cestaCookie.setMaxAge(0); // Eliminar la cookie
            response.addCookie(cestaCookie);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        // Acción para actualizar un producto
        else if ("Actualizar".equals(action)) {
            String referencia = request.getParameter("referencia");
            String descripcion = request.getParameter("descripcion");
            String precioStr = request.getParameter("precio");
            double precio = Double.parseDouble(precioStr);

            // Obtener la lista de productos del contexto de la aplicación
            List<Producto> productos = (List<Producto>) getServletContext().getAttribute("productos");
            for (Producto producto : productos) {
                if (producto.getReferencia().equals(referencia)) {
                    producto.setDescripcion(descripcion);
                    producto.setPrecio((int) precio);
                    break;
                }
            }

            // Actualizar la lista de productos en el contexto
            getServletContext().setAttribute("productos", productos);
            response.sendRedirect("acceso.jsp"); // Redirigir a acceso.jsp
        }
    }

    // Método que maneja el filtro de autenticación
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("acceso.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
