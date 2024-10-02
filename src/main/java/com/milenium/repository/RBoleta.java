package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.IBoleta;
import com.milenium.model.Boleta;

public class RBoleta implements IBoleta {

	@Override
	public String obtenerMaximoBoleta() {
		String maxBoleta = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "select substr(max(boleta_id),2) from Boleta";

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
			String sql = "insert into Boleta (id_boleta, id_cliente, total, fecha_boleta) values (?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getId_boleta());
			pst.setString(2, b.getId_cliente());
			pst.setDouble(3, b.getTotal());
			pst.setString(4, b.getFecha_boleta());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar Boleta: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}
}
