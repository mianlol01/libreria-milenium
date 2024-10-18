package com.milenium.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milenium.Mensaje;
import com.milenium.model.Cliente;
import com.milenium.repository.RCliente;
import com.milenium.service.SCliente;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet(name = "signup", urlPatterns = { "/signup" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int error = 1;
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Mensaje m = new Mensaje();
		int error = 1;
		SCliente sc = new SCliente();
		RCliente rc = new RCliente();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		if (rc.verificarUsuario("", username) == 0) {
			request.setAttribute("error", error);
			request.setAttribute("mensajesignup", m.notAvailableUser());
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}
		if (rc.verificarCorreo("", email) == 0) {
			request.setAttribute("error", error);
			request.setAttribute("mensajesignup", m.notAvailableEmail());
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}
		String id = sc.generarIdCliente();
		Cliente c = new Cliente();
		c.setApellido_cliente(lastname);
		c.setNombre_cliente(name);
		c.setUsername_cliente(username);
		c.setCorreo_cliente(email);
		c.setPassword_cliente(password);
		c.setId_cliente(id);

		int ok = rc.registrar(c);
		if (ok == 0) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("cliente", c);
		request.getSession().setAttribute("mensajeLogin", "login");
		response.sendRedirect(request.getContextPath() + "/home");
		return;
	}
}