package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.ILibro;
import com.milenium.model.Categoria;
import com.milenium.model.Libro;
import com.milenium.model.LibroReporte;

public class RLibro implements ILibro {
	// Método para obtener los libros más vendidos
	public List<LibroReporte> obtenerLibrosMasVendidos() {
		List<LibroReporte> listaLibros = new ArrayList<>();
		String sql = "SELECT l.titulo, SUM(db.cantidad) AS cantidad_vendida, SUM(db.importe) AS total_ingresos "
				+ "FROM Libro l " + "JOIN Detalle_Boleta db ON l.id_libro = db.id_libro " + "GROUP BY l.id_libro "
				+ "ORDER BY cantidad_vendida DESC;";

		try (Connection con = MySQLConexion.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				LibroReporte libro = new LibroReporte();
				libro.setTitulo(rs.getString("titulo"));
				libro.setCantidadVendida(rs.getInt("cantidad_vendida"));
				libro.setTotalIngresos(rs.getDouble("total_ingresos"));
				listaLibros.add(libro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaLibros;
	}

	@Override
	public List<Libro> listarLibros() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Libro> lista = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call ObtenerLibrosConAutor()";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Libro>();
			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setFecha_registro(rs.getString("fecha_registro"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				l.setNombre_autor(rs.getString("nombre_autor"));
				lista.add(l);
			}
		} catch (Exception e) {
			System.out.println("Error en listar Libros: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public Libro obtenerLibro(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Libro libro = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT l.id_libro, l.titulo, l.id_autor, l.sinopsis, l.fecha_registro, "
					+ "l.precio, l.stock, l.descuento, a.nombre_autor, " + "c.id_categoria, c.nombre_categoria "
					+ "FROM Libro l " + "JOIN Autor a ON l.id_autor = a.id_autor "
					+ "LEFT JOIN Libro_Categoria lc ON l.id_libro = lc.id_libro "
					+ "LEFT JOIN Categoria c ON lc.id_categoria = c.id_categoria " + "WHERE l.id_libro = ?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			libro = new Libro(); // Inicializamos el libro antes de entrar al bucle
			List<Categoria> categorias = new ArrayList<>(); // Lista para las categorías

			while (rs.next()) {
				if (libro.getId_libro() == 0) { // Solo se ejecuta una vez para llenar los datos del libro
					libro.setId_libro(rs.getInt("id_libro"));
					libro.setTitulo(rs.getString("titulo"));
					libro.setId_autor(rs.getInt("id_autor"));
					libro.setSinopsis(rs.getString("sinopsis"));
					libro.setFecha_registro(rs.getString("fecha_registro"));
					libro.setPrecio(rs.getDouble("precio"));
					libro.setStock(rs.getInt("stock"));
					libro.setDescuento(rs.getInt("descuento"));
					libro.setNombre_autor(rs.getString("nombre_autor")); // Ajusta esto en la clase Libro
				}
				// Agregar categoría si existe
				if (rs.getInt("id_categoria") != 0) {
					Categoria categoria = new Categoria();
					categoria.setId_categoria(rs.getInt("id_categoria"));
					categoria.setNombre_categoria(rs.getString("nombre_categoria"));
					categorias.add(categoria);
				}
			}
			libro.setCategorias(categorias); // Asignar la lista de categorías al libro

		} catch (Exception e) {
			System.out.println("Error en obtener Libro por id: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return libro;
	}

	@Override
	public int registrarLibro(Libro l, int[] idsCategorias) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pstLibro = null;
		PreparedStatement pstCategoria = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false); // Desactivamos el auto-commit para manejar transacciones

			// Insertar el libro
			String sqlLibro = "INSERT INTO Libro (titulo, id_autor, sinopsis, precio, stock, descuento) VALUES (?, ?, ?, ?, ?, ?)";
			pstLibro = con.prepareStatement(sqlLibro, Statement.RETURN_GENERATED_KEYS);
			pstLibro.setString(1, l.getTitulo());
			pstLibro.setInt(2, l.getId_autor());
			pstLibro.setString(3, l.getSinopsis());
			pstLibro.setDouble(4, l.getPrecio());
			pstLibro.setInt(5, l.getStock());
			pstLibro.setInt(6, l.getDescuento());
			pstLibro.executeUpdate();

			// Obtener el id_libro generado automáticamente
			rs = pstLibro.getGeneratedKeys();
			int idLibroGenerado = 0;
			if (rs.next()) {
				idLibroGenerado = rs.getInt(1);
			}

			// Insertar las categorías para el libro
			String sqlCategoria = "INSERT INTO Libro_Categoria (id_libro, id_categoria) VALUES (?, ?)";
			pstCategoria = con.prepareStatement(sqlCategoria);
			for (int idCategoria : idsCategorias) {
				pstCategoria.setInt(1, idLibroGenerado);
				pstCategoria.setInt(2, idCategoria);
				pstCategoria.executeUpdate();
			}

			con.commit(); // Confirmamos la transacción
			ok = 1;
		} catch (Exception e) {
			try {
				if (con != null) {
					con.rollback(); // Revertimos la transacción si algo falla
				}
			} catch (SQLException ex) {
				System.out.println("Error en rollback: " + ex.getMessage());
			}
			System.out.println("Error en registrar Libro: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstLibro != null)
					pstLibro.close();
				if (pstCategoria != null)
					pstCategoria.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar recursos: " + e.getMessage());
			}
		}
		return ok;
	}

	@Override
	public int eliminarLibro(int id) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "DELETE FROM Libro WHERE id_libro = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar libro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public List<Libro> listarDestacados() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Libro> lista = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call ObtenerLibrosDestacados()";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Libro>();
			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setFecha_registro(rs.getString("fecha_registro"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				l.setNombre_autor(rs.getString("nombre_autor"));
				lista.add(l);
			}
		} catch (Exception e) {
			System.out.println("Error en listar Libros destacados: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public List<Libro> buscador(String frase) {
		List<Libro> lista = new ArrayList<>();
		if (frase == null) {
			return lista;
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			if (con == null) {
				throw new SQLException("No se pudo establecer la conexión a la base de datos.");
			}
			String sql = "call BuscarLibrosPorFrase(?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, frase);
			rs = pst.executeQuery();

			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setFecha_registro(rs.getString("fecha_registro"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				l.setNombre_autor(rs.getString("nombre_autor"));
				lista.add(l);
			}
		} catch (SQLException e) {
			System.out.println("Error en buscar Libros: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				MySQLConexion.closeConexion(con);
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos: " + e.getMessage());
			}
		}
		return lista;
	}

	@Override
	public List<Libro> filtrarCategoria(int id) {
		List<Libro> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "call ObtenerLibrosPorCategoria(?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id); // Establecer el parámetro de la categoría
			rs = pst.executeQuery();
			lista = new ArrayList<Libro>();

			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setFecha_registro(rs.getString("fecha_registro"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				l.setNombre_autor(rs.getString("nombre_autor"));
				lista.add(l);
			}

		} catch (Exception e) {
			System.out.println("Error en listarPorCategoria: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}

		return lista;
	}

	@Override
	public int actualizar(Libro l) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "UPDATE Libro SET precio = ?, stock = ?, descuento = ? WHERE id_libro = ?;";
			pst = con.prepareStatement(sql);
			pst.setDouble(1, l.getPrecio());
			pst.setInt(2, l.getDescuento());
			pst.setInt(3, l.getStock());
			pst.setInt(4, l.getId_libro());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en Actualizar libro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public int actualizarPrecioDescuento(int id, double price, int discount) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "UPDATE Libro SET precio = ?, descuento = ? WHERE id_libro = ?;";
			pst = con.prepareStatement(sql);
			pst.setDouble(1, price);
			pst.setInt(2, discount);
			pst.setInt(3, id);
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en Actualizar libro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public int actualizarStock(int id, int cantidad) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		// Obtener libro
		Libro l = obtenerLibro(id);
		int nuevoStock = l.getStock() - cantidad;
		try {
			con = MySQLConexion.getConexion();
			String sql = "UPDATE Libro SET stock = ? WHERE id_libro = ?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nuevoStock);
			pst.setInt(2, id);
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en Actualizar libro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public List<Libro> librosSimilares(int id) {
		List<Libro> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "call BuscarLibrosSimilares(?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id); // Establecer el parámetro de la categoría
			rs = pst.executeQuery();
			lista = new ArrayList<Libro>();

			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setFecha_registro(rs.getString("fecha_registro"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				l.setNombre_autor(rs.getString("nombre_autor"));
				lista.add(l);
			}

		} catch (Exception e) {
			System.out.println("Error en listarPorCategoria: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}

		return lista;
	}

	@Override
	public int configurarCategoria(int idLibro, int[] categorias) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "INSERT INTO Libro_Categoria (id_libro, id_categoria) VALUES (?, ?)";
			pst = con.prepareStatement(sql);

			for (int idCategoria : categorias) {
				// Asignamos los valores al PreparedStatement
				pst.setInt(1, idLibro);
				pst.setInt(2, idCategoria);
				// Ejecutamos la inserción
				ok += pst.executeUpdate(); // Incrementamos ok si se inserta correctamente
			}
		} catch (Exception e) {
			System.out.println("Error en registrar categorías: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok; // Retorna la cantidad de inserciones exitosas
	}
}