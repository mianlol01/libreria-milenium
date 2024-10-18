package com.milenium.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milenium.model.Cart;
import com.milenium.model.Cliente;
import com.milenium.model.Libro;
import com.milenium.repository.RLibro;
import com.milenium.service.ResultadoBoleta;
import com.milenium.service.SBoleta;
import com.milenium.service.SVenta;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(name = "cart", urlPatterns = { "/cart", "/carro", "/carrito" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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
		String mensaje = (String) request.getSession().getAttribute("mensaje");

		if (mensaje != null) {
			int m = 1;
			request.setAttribute("mensaje", m);
			request.getSession().removeAttribute("mensaje"); // Elimina el mensaje después de mostrarlo
		}
		RLibro rl = new RLibro();
		List<Libro> listaRecomendados = rl.listarDestacados();
		request.setAttribute("listaRecomendados", listaRecomendados);
		request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "add":
			agregarCarrito(request, response);
			break;
		case "edit":
			editarCarrito(request, response);
			break;
		case "delete":
			eliminarCarrito(request, response);
			break;
		case "buy":
			finalizarCompra(request, response);
			break;

		default:
			// Manejar la acción predeterminada
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
			break;
		}

	}

	protected void eliminarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Eliminando libro del carrito");

		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// Buscar y eliminar el detalle boleta con el id_libro
		cart.eliminarPorIdLibro(id_libro);
		int cantidad_articulos = cart.cantidadArticulos();
		double total = cart.total();
		System.out.println("Cantidad de artículos tras eliminar: " + cantidad_articulos);
		System.out.println("Total a pagar tras eliminar: " + total);

		// Actualizar atributos de la sesión
		request.getSession().setAttribute("cart", cart);

		response.sendRedirect(request.getRequestURI()); // Redirigir a la misma página
	}

	protected void editarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.reemplazarDetalleBoleta(id_libro, cantidad);
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect(request.getRequestURI());
	}

	protected void agregarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		ResultadoBoleta result = new ResultadoBoleta();
		result = SBoleta.generarDetalleBoleta(id_libro, cantidad);
		int agregar = cart.agregarCarro(result.getDetalle());
		System.out.println(agregar);
		if (agregar != -1) {
			RLibro rl = new RLibro();
			Libro libro = rl.obtenerLibro(id_libro);
			if (libro == null) {
				response.sendRedirect("/WEB-INF/views/error.jsp");
				return;
			}
			List<Libro> listaSimilares = rl.librosSimilares(id_libro);
			System.out.println("restante: " + agregar);
			request.setAttribute("restante", agregar);
			request.setAttribute("libro", libro);
			request.setAttribute("listaSimilares", listaSimilares);
			request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect(request.getRequestURI());
	}

	protected void finalizarCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cliente c = (Cliente) request.getSession().getAttribute("cliente");
		if (c == null) {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}

		SVenta sv = new SVenta();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		sv.realizarVenta(c.getId_cliente(), cart);
		cart = new Cart();
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("mensaje", "1");
		response.sendRedirect(request.getContextPath() + "/cart");
		return;
	}

}
