package com.milenium.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milenium.model.Autor;
import com.milenium.model.CategoriaReporte;
import com.milenium.model.Cliente;
import com.milenium.model.ClienteFrecuente;
import com.milenium.model.Empleado;
import com.milenium.model.Libro;
import com.milenium.model.LibroReporte;
import com.milenium.repository.RAutor;
import com.milenium.repository.RCategoria;
import com.milenium.repository.RCliente;
import com.milenium.repository.REmpleado;
import com.milenium.repository.RLibro;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "admin", urlPatterns = { "/admin" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mensaje = (String) request.getSession().getAttribute("mensaje");
		String cssmensaje = (String) request.getSession().getAttribute("cssmensaje");
		if (mensaje != null) {
			request.setAttribute("mensaje", mensaje);
			request.getSession().removeAttribute("mensaje");
		}
		if (cssmensaje != null) {
			request.setAttribute("cssmensaje", cssmensaje);
			request.getSession().removeAttribute("cssmensaje");
		}
		Empleado e = (Empleado) request.getSession().getAttribute("admin");
		if (e == null) {
			request.getRequestDispatcher("/WEB-INF/views/admin-login.jsp").forward(request, response);
			return;
		}
		String action = request.getParameter("action");
		switch (action) {
		case null: {
			request.getRequestDispatcher("/WEB-INF/views/admin-home.jsp").forward(request, response);
			break;
		}
		case "reportes": {
			reporte(request, response);
			break;
		}
		case "reporte-clientes": {
			reportesClientes(request, response);
			break;
		}
		case "reporte-mas-vendidos": {
			reporteLibrosMasVendidos(request, response);
			break;
		}
		case "reporte-categoria": {
			reporteCategoria(request, response);
			break;
		}
		case "cliente": {
			cliente(request, response);
			break;
		}
		case "clientes": {
			clientes(request, response);
			break;
		}
		case "libros": {
			libros(request, response);
			break;
		}
		case "editar-libro": {
			editarLibro(request, response);
			break;
		}
		case "logout": {
			logout(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		switch (action) {
		case "login": {
			login(request, response);
			break;
		}
		case "editar-libro": {
			editarLibro(request, response, true);
			break;
		}
		case "registrar-libro": {
			registrarLibro(request, response);
			break;
		}
		case "registrar-autor": {
			registrarAutor(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}

	}
	private void reportesClientes(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RCliente rc = new RCliente();
		List<ClienteFrecuente> listaClientes = rc.obtenerClientesFrecuentes();
		request.setAttribute("listaClientes", listaClientes);
		request.getRequestDispatcher("/WEB-INF/views/admin-reporte-clientes.jsp").forward(request, response);
	}

	private void reporteLibrosMasVendidos(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RLibro rl = new RLibro();
		List<LibroReporte> listaLibros = rl.obtenerLibrosMasVendidos();

		request.setAttribute("listaLibros", listaLibros);
		request.getRequestDispatcher("/WEB-INF/views/admin-reporte-mas-vendidos.jsp").forward(request, response);
	}

	private void reporte(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/admin-reporte.jsp").forward(request, response);
	}

	private void reporteCategoria(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RCategoria rc = new RCategoria();
		List<CategoriaReporte> listaReporte = rc.obtenerReporteCategoriasVendidas();
		request.setAttribute("listaReporte", listaReporte);
		request.getRequestDispatcher("/WEB-INF/views/admin-reporte-categoria.jsp").forward(request, response);
	}

	private void cliente(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String idCliente = request.getParameter("id_cliente");
		RCliente rc = new RCliente();
		Cliente cliente = rc.obtenerClienteConBoletasYDetalles(idCliente);
		request.setAttribute("cliente", cliente);
		request.getRequestDispatcher("/WEB-INF/views/admin-cliente.jsp").forward(request, response);
	}

	// Método GET para mostrar el formulario de edición
	private void clientes(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RCliente rc = new RCliente();
		List<Cliente> clientes = rc.obtenerClientesConBoletas();
		request.setAttribute("clientes", clientes);
		request.getRequestDispatcher("/WEB-INF/views/admin-clientes.jsp").forward(request, response);
	}

	// Método GET para mostrar el formulario de edición
	private void editarLibro(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		RLibro rl = new RLibro();
		Libro l = rl.obtenerLibro(id_libro);
		request.setAttribute("libro", l);
		request.getRequestDispatcher("/WEB-INF/views/admin-editar-libro.jsp").forward(request, response);
	}

	// Método POST para procesar el formulario de edición
	private void editarLibro(HttpServletRequest request, HttpServletResponse response, boolean isPost)
			throws IOException, ServletException {
		if (isPost) {
			int id_libro = Integer.parseInt(request.getParameter("id_libro"));
			double precio = Double.parseDouble(request.getParameter("precio"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			int descuento = Integer.parseInt(request.getParameter("descuento"));
			RLibro rl = new RLibro();
			Libro l = new Libro();
			l.setId_libro(id_libro);
			l.setPrecio(precio);
			l.setStock(stock);
			l.setDescuento(descuento);
			rl.actualizar(l);
			request.getSession().setAttribute("cssmensaje", "alert-success");
			request.getSession().setAttribute("mensaje", "Libro actualizado correctamente");
			response.sendRedirect(request.getContextPath() + "/admin?action=editar-libro&id_libro=" + l.getId_libro()); // necesario
		}
	}

	private void registrarAutor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String autor = request.getParameter("autor");
		RAutor ra = new RAutor();
		Autor a = new Autor();
		a.setNombre_autor(autor);
		int resultado = ra.registrarAutor(a);
		if (resultado == -1) {
			request.getSession().setAttribute("cssmensaje", "alert-danger");
			request.getSession().setAttribute("mensaje", "El autor ingresado ya estaba registrado");
			response.sendRedirect(request.getContextPath() + "/admin?action=libros");
			return;
		}
		if (resultado == 0) {
			request.getSession().setAttribute("cssmensaje", "alert-danger");
			request.getSession().setAttribute("mensaje", "Problemas para registrar autor");
			response.sendRedirect(request.getContextPath() + "/admin?action=libros");
			return;
		}
		request.getSession().setAttribute("cssmensaje", "alert-success");
		request.getSession().setAttribute("mensaje", "Autor registrado correctamente");
		response.sendRedirect(request.getContextPath() + "/admin?action=libros");
		return;
	}

	private void registrarLibro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int descuento = Integer.parseInt(request.getParameter("descuento"));
		String sinopsis = request.getParameter("sinopsis");
		String[] categorias = request.getParameterValues("categorias");
		int[] idsCategorias = new int[0];
		if (categorias != null) {
			idsCategorias = Arrays.stream(categorias).mapToInt(Integer::parseInt).toArray();
		}
		for (int cat : idsCategorias) {
			System.out.println(cat);
		}
		RAutor ra = new RAutor();
		Autor a = ra.obtenerAutor(autor);
		RLibro rl = new RLibro();
		if (a == null) {
			request.getSession().setAttribute("cssmensaje", "alert-danger");
			request.getSession().setAttribute("mensaje", "Autor no encontrado, porfavor ingrese un autor registrado");
			response.sendRedirect(request.getContextPath() + "/admin?action=libros");
			return;
		}
		Libro l = new Libro();
		l.setTitulo(titulo);
		l.setId_autor(a.getId_autor());
		l.setPrecio(precio);
		l.setStock(stock);
		l.setDescuento(descuento);
		l.setSinopsis(sinopsis);
		rl.registrarLibro(l, idsCategorias);
		System.out.println("esta es una frase con tilde acá acá sí");
		System.out.println(titulo);
		request.getSession().setAttribute("cssmensaje", "alert-success");
		request.getSession().setAttribute("mensaje", "Libro registrado correctamente");
		response.sendRedirect(request.getContextPath() + "/admin?action=libros");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("admin_username");
		String password = request.getParameter("admin_password");
		REmpleado re = new REmpleado();
		Empleado e = re.ingreso(username, password);
		if (e == null) {
			request.getSession().setAttribute("mensaje", "Usuario o Contraseña incorrectos");
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		request.getSession().setAttribute("admin", e);
		request.getSession().setAttribute("mensaje", "Sesión iniciada con exito");
		response.sendRedirect(request.getContextPath() + "/admin");
		return;
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		request.getSession().setAttribute("mensaje", "Sesión cerrada con exito");
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	private void libros(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RLibro rl = new RLibro();
		RAutor ra = new RAutor();
		List<Autor> listaAutores = ra.listarAutores();
		List<Libro> listaLibros = rl.listarLibros();
		request.getSession().setAttribute("listaLibros", listaLibros);
		request.getSession().setAttribute("listaAutores", listaAutores);
		request.getRequestDispatcher("/WEB-INF/views/admin-libros.jsp").forward(request, response);
	}

}
