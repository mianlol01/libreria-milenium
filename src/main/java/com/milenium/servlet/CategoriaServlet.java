package com.milenium.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milenium.model.Categoria;
import com.milenium.model.Libro;
import com.milenium.repository.RCategoria;
import com.milenium.repository.RLibro;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet(name = "category", urlPatterns = { "/category", "/categoria" })
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriaServlet() {
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
		int idCategoria = Integer.parseInt(request.getParameter("category"));
		RLibro rl = new RLibro();
		RCategoria rc = new RCategoria();
		Categoria c = rc.obtenerCategoria(idCategoria);
		if (c == null) {
			response.sendRedirect("/WEB-INF/views/error.jsp");
		}
		List<Libro> listaLibrosPorCategoria = rl.filtrarCategoria(idCategoria);
		request.setAttribute("categoria", c);
		request.setAttribute("listaLibrosPorCategoria", listaLibrosPorCategoria);
		request.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(request, response);
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
