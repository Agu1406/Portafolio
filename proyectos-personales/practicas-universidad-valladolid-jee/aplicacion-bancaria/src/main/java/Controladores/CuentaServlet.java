package Controladores;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Modelos.Cliente;
import Modelos.Cuenta;

/**
 * Servlet implementation class CuentaServlet
 */
@WebServlet("/CuentaServlet")
public class CuentaServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String page = request.getParameter("decision");
		if(page.equalsIgnoreCase("Prestamo")) {
			response.sendRedirect("Prestamo.jsp");
		}

		if(session.getAttribute("usuario") != null) {
			// 1234567890
			String ISBN = request.getParameter("ISBN").toString();
			
			Cliente titular = new Cliente(session.getAttribute("usuario").toString(),session.getAttribute("password").toString());
			
			Double saldo = Double.parseDouble(request.getParameter("saldo"));
			
			if(!ISBN.matches("\\d{10}")) {
				request.setAttribute("error", "Número de cuenta inválido");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Mensaje.jsp");
				dispatcher.forward(request, response);
			} else {
				Cuenta cuenta = new Cuenta(ISBN, titular.getUsuario(), saldo);
				request.setAttribute("mensaje", "Cuenta dada de alta");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Mensaje.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
