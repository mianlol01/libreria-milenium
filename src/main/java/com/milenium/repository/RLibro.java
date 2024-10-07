package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.ILibro;
import com.milenium.model.Libro;

public class RLibro implements ILibro {

	@Override
	public List<Libro> listarLibros() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Libro> lista = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Libro";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Libro>();
			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setId_categoria(rs.getInt("id_categoria"));
				l.setFecha_publicacion(rs.getString("fecha_publicacion"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
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
			String sql = "SELECT * FROM Libro WHERE id_libro = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				libro = new Libro();
				libro.setId_libro(rs.getInt("id_libro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setId_autor(rs.getInt("id_autor"));
				libro.setSinopsis(rs.getString("sinopsis"));
				libro.setId_categoria(rs.getInt("id_categoria"));
				libro.setFecha_publicacion(rs.getString("fecha_publicacion"));
				libro.setPrecio(rs.getDouble("precio"));
				libro.setStock(rs.getInt("stock"));
				libro.setDescuento(rs.getInt("descuento"));
			}
		} catch (Exception e) {
			System.out.println("Error en obtener Libro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return libro;
	}

	@Override
	public int registrarLibro(Libro l) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "INSERT INTO Libro (titulo, id_autor, sinopsis, id_categoria, fecha_publicacion, precio, stock, descuento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, l.getTitulo());
			pst.setInt(2, l.getId_autor());
			pst.setString(3, l.getSinopsis());
			pst.setInt(4, l.getId_categoria());
			pst.setString(5, l.getFecha_publicacion());
			pst.setDouble(6, l.getPrecio());
			pst.setInt(7, l.getStock());
			pst.setInt(8, l.getDescuento());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar Libro: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
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
				l.setId_categoria(rs.getInt("id_categoria"));
				l.setFecha_publicacion(rs.getString("fecha_publicacion"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				l.setNombre_autor(rs.getString("nombre_autor"));
				l.setNombre_categoria(rs.getString("nombre_categoria"));
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
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Libro> lista = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call BuscarLibrosPorFrase(?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, frase);
			rs = pst.executeQuery();
			lista = new ArrayList<Libro>();
			while (rs.next()) {
				Libro l = new Libro();
				l.setId_libro(rs.getInt("id_libro"));
				l.setTitulo(rs.getString("titulo"));
				l.setId_autor(rs.getInt("id_autor"));
				l.setSinopsis(rs.getString("sinopsis"));
				l.setId_categoria(rs.getInt("id_categoria"));
				l.setFecha_publicacion(rs.getString("fecha_publicacion"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				lista.add(l);
			}
		} catch (Exception e) {
			System.out.println("Error en buscar Libros: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
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
			String sql = "SELECT * FROM Libro WHERE id_categoria = ?";
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
				l.setId_categoria(rs.getInt("id_categoria"));
				l.setFecha_publicacion(rs.getString("fecha_publicacion"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
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
		//Obtener libro
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
			String sql = "SELECT * FROM Libro WHERE id_categoria = ?";
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
				l.setId_categoria(rs.getInt("id_categoria"));
				l.setFecha_publicacion(rs.getString("fecha_publicacion"));
				l.setPrecio(rs.getDouble("precio"));
				l.setStock(rs.getInt("stock"));
				l.setDescuento(rs.getInt("descuento"));
				lista.add(l);
			}

		} catch (Exception e) {
			System.out.println("Error en listarPorCategoria: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}

		return lista;
	}
}