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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action");
		if (action == null) {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}

		else {
			switch (action) {
			case "logout":
				logout(request, response);
				break;
			default:
				response.getWriter().println("Acción no válida.");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int error = 0;
		Mensaje m = new Mensaje();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RCliente rc = new RCliente();
		Cliente c = rc.ingreso(username, password);
		if (c == null) {
			request.setAttribute("error", error);
			request.setAttribute("mensaje", m.errorLogin());
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("cliente", c);
		request.getSession().setAttribute("mensajeLogin", "login");
		response.sendRedirect(request.getContextPath() + "/home");
		return;
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		request.getSession().setAttribute("mensajeLogin", "logout");
		response.sendRedirect(request.getContextPath() + "/home");
	}
}