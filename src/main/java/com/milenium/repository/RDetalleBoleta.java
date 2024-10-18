package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.IDetalleBoleta;
import com.milenium.model.DetalleBoleta;

public class RDetalleBoleta implements IDetalleBoleta {

	@Override
	public ArrayList<DetalleBoleta> listarDetallePorIdBoleta(String id_boleta) {
		ArrayList<DetalleBoleta> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT * FROM Detalle_Boleta WHERE id_boleta = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id_boleta);
			rs = pst.executeQuery();
			lista = new ArrayList<DetalleBoleta>();

			while (rs.next()) {
				DetalleBoleta db = new DetalleBoleta();
				db.setId_detalle_boleta(rs.getInt("id_detalle_boleta"));
				db.setId_boleta(rs.getString("id_boleta"));
				db.setId_libro(rs.getInt("id_libro"));
				db.setCantidad(rs.getInt("cantidad"));
				db.setImporte(rs.getDouble("importe"));
				db.setTitulo(rs.getString("titulo"));
				db.setPrecio_unitario(rs.getDouble("precio_unitario"));
				db.setDescuento_unidad(rs.getDouble("descuento_unidad"));
				db.setTotal(rs.getDouble("precio_total"));
				db.setDescuento_total(rs.getDouble("descuento_total"));
				lista.add(db);
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
			String sql = "INSERT INTO Detalle_Boleta (id_boleta, id_libro, titulo, precio_unitario, descuento_unidad, cantidad, precio_total, descuento_total, importe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, b.getId_boleta());
			pst.setInt(2, b.getId_libro());
			pst.setString(3, b.getTitulo());
			pst.setDouble(4, b.getPrecio_unitario());
			pst.setDouble(5, b.getDescuento_unidad());
			pst.setInt(6, b.getCantidad());
			pst.setDouble(7, b.getTotal());
			pst.setDouble(8, b.getDescuento_total());
			pst.setDouble(9, b.getImporte());		
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar DetalleBoleta: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
