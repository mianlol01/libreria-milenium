package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.ICategoria;
import com.milenium.model.Categoria;

public class RCategoria implements ICategoria {

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