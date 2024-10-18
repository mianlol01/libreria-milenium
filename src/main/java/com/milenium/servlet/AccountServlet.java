package com.milenium.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milenium.model.Boleta;
import com.milenium.model.Cliente;
import com.milenium.repository.RCliente;
import com.milenium.service.SBoleta;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet(name = "account", urlPatterns = { "/account", "/cuenta" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionPost = (String) request.getSession().getAttribute("actionPost");

		if (actionPost != null) {
			System.out.println("action =" + actionPost);
			request.setAttribute("action", actionPost);
			request.getSession().removeAttribute("actionPost");
			manage(request, response);
			return;
		}

		String action = request.getParameter("action");
		if (action == null) {
			request.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(request, response);
			return;
		} else {
			switch (action) {
			case "manage":
				manage(request, response);
				break;
			case "receipt":
				receipt(request, response);
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
		String action = request.getParameter("action");
		switch (action) {
		case "edit": {
			edit(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	private void receipt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SBoleta sb = new SBoleta();
		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		List<Boleta> listaBoletas= sb.listarBoletasCliente(c.getId_cliente());
		request.setAttribute("listaBoletas", listaBoletas);
		request.getRequestDispatcher("/WEB-INF/views/receipt.jsp").forward(request, response);
		return;
	}

	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensajeUsername = (String) request.getSession().getAttribute("mensaje_username");
		String mensajeCorreo = (String) request.getSession().getAttribute("mensaje_correo");
		String mensaje_ok = (String) request.getSession().getAttribute("mensaje_ok");

		if (mensajeUsername != null) {
			request.setAttribute("mensaje_username", mensajeUsername);
			request.getSession().removeAttribute("mensaje_username");
			request.getRequestDispatcher("/WEB-INF/views/accountconfig.jsp").forward(request, response);
			return;
		}
		if (mensajeCorreo != null) {
			request.setAttribute("mensaje_correo", mensajeCorreo);
			request.getSession().removeAttribute("mensaje_correo");
			request.getRequestDispatcher("/WEB-INF/views/accountconfig.jsp").forward(request, response);
			return;
		}
		if (mensaje_ok != null) {
			request.setAttribute("mensaje_ok", mensaje_ok);
			request.getSession().removeAttribute("mensaje_ok");
			request.getRequestDispatcher("/WEB-INF/views/accountconfig.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/accountconfig.jsp").forward(request, response);
		return;
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		RCliente rc = new RCliente();
		String nombre = request.getParameter("nombre_cliente");
		String apellido = request.getParameter("apellido_cliente");
		String correo = request.getParameter("correo_cliente");
		String username = request.getParameter("username_cliente");
		String password = request.getParameter("password_cliente");
		request.getSession().setAttribute("actionPost", "manage");
		int validarUsuario = rc.verificarUsuario(c.getId_cliente(), username);
		if (validarUsuario == 0) {
			System.out.println("usuario no disponible");
			request.getSession().setAttribute("mensaje_username", "Usuario no disponible");
			response.sendRedirect(request.getContextPath() + "/account");
			return;
		}
		int validarCorreo = rc.verificarCorreo(c.getId_cliente(), correo);
		if (validarCorreo == 0) {
			System.out.println("correo no disponible");
			request.getSession().setAttribute("mensaje_correo", "Correo no disponible");
			response.sendRedirect(request.getContextPath() + "/account");
			return;
		}

		
		c.setApellido_cliente(apellido);
		c.setCorreo_cliente(correo);
		c.setNombre_cliente(nombre);
		c.setPassword_cliente(password);
		c.setUsername_cliente(username);
		rc.actualizar(c);
		request.getSession().setAttribute("cliente", c);
		request.getSession().setAttribute("mensaje_ok", "Datos cambiados correctamente");
		response.sendRedirect(request.getContextPath() + "/account");
		return;
	}
}
