package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.IBoleta;
import com.milenium.model.Boleta;

public class RBoleta implements IBoleta {

	@Override
	public ArrayList<Boleta> obtenerBoletasCliente(String id_cliente) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Boleta> listaBoletas = new ArrayList<Boleta>();
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Boleta where id_cliente = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id_cliente);
			rs = pst.executeQuery();
			while (rs.next()) {
				Boleta b = new Boleta();
				b.setId_boleta(rs.getString("id_boleta"));
				b.setId_cliente(rs.getString("id_cliente"));
				b.setFecha_boleta(rs.getString("fecha_boleta"));
				b.setTotal(rs.getDouble("total"));
				listaBoletas.add(b);
			}
		} catch (Exception e) {
			System.out.println("Error en listar Boletas: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return listaBoletas;
	}

	@Override
	public String obtenerMaximoBoleta() {
		String maxBoleta = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "select substr(max(id_boleta),2) from Boleta";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				maxBoleta = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("Error en obtenerMaximoBoleta: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar conexión: " + e.getMessage());
			}
		}

		return maxBoleta;
	}

	@Override
	public int registrar(Boleta b) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into Boleta (id_boleta, id_cliente, total) values (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getId_boleta());
			pst.setString(2, b.getId_cliente());
			pst.setDouble(3, b.getTotal());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar Boleta: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
