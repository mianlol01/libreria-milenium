package com.milenium.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.milenium.model.Libro;
import com.milenium.repository.RLibro;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(name = "product", urlPatterns = { "/product", "/libro" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		int idLibro = Integer.parseInt(request.getParameter("book"));
		RLibro rl = new RLibro();
		Libro libro = rl.obtenerLibro(idLibro);
		if (libro != null) {
			session.setAttribute("libro", libro);
			request.setAttribute("libro", libro);
			List<Libro> listaSimilares = rl.librosSimilares(idLibro);
			request.setAttribute("listaSimilares", listaSimilares);
			request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
		} else {
			response.sendRedirect("/WEB-INF/views/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
