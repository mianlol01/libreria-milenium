package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.IDetalleBoleta;
import com.milenium.model.DetalleBoleta;

public class RDetalleBoleta implements IDetalleBoleta {

	@Override
	public List<DetalleBoleta> listarBoleta(String id) {
		List<DetalleBoleta> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT * FROM Detalle_Boleta WHERE id_detalle_boleta = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			lista = new ArrayList<DetalleBoleta>();

			while (rs.next()) {
				DetalleBoleta db = new DetalleBoleta();
				db.setId_detalle_boleta(rs.getInt("id_detalle_boleta"));
				db.setId_boleta(rs.getString("id_boleta"));
				db.setId_libro(rs.getInt("id_libro"));
				db.setCantidad(rs.getInt("cantidad"));
				db.setImporte(rs.getDouble("importe"));
			}
		} catch (Exception e) {
			System.out.println("Error en listar DetalleBoleta: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}

		return lista;
	}

	@Override
	public int registrar(DetalleBoleta b) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "INSERT INTO Detalle_Boleta (id_boleta, id_libro, cantidad, importe) VALUES (?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getId_boleta());
			pst.setInt(2, b.getId_libro());
			pst.setInt(3, b.getCantidad());
			pst.setDouble(4, b.getImporte());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar DetalleBoleta: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
