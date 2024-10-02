package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.IAutor;
import com.milenium.model.Autor;

public class RAutor implements IAutor {

	@Override
	public List<Autor> listarAutores() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Autor> lista = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Autor";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Autor>();
			while (rs.next()) {
				Autor a = new Autor();
				a.setId_autor(rs.getInt("id_autor"));
				a.setNombre_autor(rs.getString("nombre_autor"));
				;
				lista.add(a);
			}
		} catch (Exception e) {
			System.out.println("Error en listar Autor: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public Autor obtenerAutor(int id) {
		Autor a = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT * FROM Autor WHERE id_autor = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				a = new Autor();
				a.setId_autor(rs.getInt("id_autor"));
				a.setNombre_autor(rs.getString("nombre_autor"));
			}
		} catch (Exception e) {
			System.out.println("Error en obtener Autor con id "+id+" : " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return a;
	}

	@Override
	public int registrarAutor(Autor a) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into Autor (nombre_autor) values (?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNombre_autor());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar Autor: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}
	@Override
	public int eliminarAutor(int id) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "DELETE FROM Autor WHERE id_autor = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar autor: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
