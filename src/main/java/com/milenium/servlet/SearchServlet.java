package com.milenium.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milenium.model.Libro;
import com.milenium.repository.RLibro;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "search", urlPatterns = { "/search", "/buscar" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		String frase = "";
		frase = request.getParameter("q");
		System.out.println(frase);
		if (frase == null || frase.trim().isEmpty()) {
			System.out.println("la frase está vacía");
			request.setAttribute("frase", frase);
			request.getRequestDispatcher("/WEB-INF/views/notfound.jsp").forward(request, response);
			return;
		}
		RLibro rl = new RLibro();
		List<Libro> listaLibrosEncontrados = rl.buscador(frase);
		if (listaLibrosEncontrados.size() == 0) {
			request.setAttribute("frase", frase);
			System.out.println("0 libros encontrados");
			request.getRequestDispatcher("/WEB-INF/views/notfound.jsp").forward(request, response);
			return;
		}
		System.out.println("aca es :)");
		request.setAttribute("frase", frase);
		request.setAttribute("listaLibrosEncontrados", listaLibrosEncontrados);
		request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request, response);
		return;
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
