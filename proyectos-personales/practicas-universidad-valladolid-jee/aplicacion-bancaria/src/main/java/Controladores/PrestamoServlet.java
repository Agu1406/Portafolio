package Controladores;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import Modelos.Prestamo;

/**
 * Servlet implementation class PrestamoServlet
 */
@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("prestamo") == null) {
			String nPrestamo = UUID.randomUUID().toString();
			String fecha = LocalDate.now().toString();
			Double cantidad = Double.parseDouble(request.getParameter("cantidad"));

			Prestamo prestamo = new Prestamo(nPrestamo, fecha, cantidad);

			session.setAttribute("prestamo", prestamo);
			request.setAttribute("mensaje", "Se ha registrado correctamente el prestamo");

			RequestDispatcher distpacher = request.getRequestDispatcher("Mensaje.jsp");
			distpacher.forward(request, response);
		} else {
			request.setAttribute("error", "Ya has realizado otro prestamo");
			
			RequestDispatcher distpacher = request.getRequestDispatcher("Mensaje.jsp");
			distpacher.forward(request, response);
		}
	}

}
