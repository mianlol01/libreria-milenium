package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.ICategoria;
import com.milenium.model.Categoria;
import com.milenium.model.CategoriaReporte;

public class RCategoria implements ICategoria {

	public List<CategoriaReporte> obtenerReporteCategoriasVendidas() {
		List<CategoriaReporte> listaReporte = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion(); // Conexión a la base de datos
			String sql = "SELECT c.nombre_categoria AS Categoria, COUNT(db.id_detalle_boleta) AS Cantidad_Vendida "
					+ "FROM Categoria c " + "LEFT JOIN Libro_Categoria lc ON c.id_categoria = lc.id_categoria "
					+ "LEFT JOIN Libro l ON lc.id_libro = l.id_libro "
					+ "LEFT JOIN Detalle_Boleta db ON l.id_libro = db.id_libro " + "GROUP BY c.id_categoria "
					+ "ORDER BY Cantidad_Vendida DESC;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				String nombreCategoria = rs.getString("Categoria");
				int cantidadVendida = rs.getInt("Cantidad_Vendida");
				CategoriaReporte reporte = new CategoriaReporte(nombreCategoria, cantidadVendida);
				listaReporte.add(reporte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLConexion.closeConexion(con); // Cerrar la conexión
		}
		return listaReporte;
	}

	@Override
	public List<Categoria> listarCategoria() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Categoria> lista = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Categoria";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Categoria>();
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setId_categoria(rs.getInt("id_categoria"));
				c.setNombre_categoria(rs.getString("nombre_categoria"));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en listar Categorias: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public Categoria obtenerCategoria(int id) {
		Categoria categoria = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT * FROM Categoria WHERE id_categoria = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				categoria = new Categoria();
				categoria.setId_categoria(rs.getInt("id_categoria"));
				categoria.setNombre_categoria(rs.getString("nombre_categoria"));
			}
		} catch (Exception e) {
			System.out.println("Error en obtener Categoria: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return categoria;
	}
}