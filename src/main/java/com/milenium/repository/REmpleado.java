package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.IEmpleado;
import com.milenium.model.Empleado;

public class REmpleado implements IEmpleado {

	@Override
	public List<Empleado> listadoEmpleados() {
		List<Empleado> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Empleado";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Empleado>();
			while (rs.next()) {
				Empleado e = new Empleado();
				e.setId_empleado(rs.getString("id_empleado"));
				e.setNombre_empleado(rs.getString("nombre_empleado"));
				e.setApellido_empleado(rs.getString("apellido_empleado"));
				e.setUsername_empleado(rs.getString("username_empleado"));
				e.setPassword_empleado(rs.getString("password_empleado"));
				lista.add(e);
			}
		} catch (Exception e) {
			System.out.println("Error en Listar Empleados: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public Empleado ingreso(String username, String password) {
		Empleado e = null;
		if (username == null || password == null) {
			return e;
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call ValidarEmpleado(?, ?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				e = new Empleado();
				e.setId_empleado(rs.getString("id_empleado"));
				e.setNombre_empleado(rs.getString("nombre_empleado"));
				e.setApellido_empleado(rs.getString("apellido_empleado"));
				e.setUsername_empleado(rs.getString("username_empleado"));
				e.setPassword_empleado(rs.getString("password_empleado"));
			}

		} catch (SQLException x) {
			System.out.println("Error en validar Empleado: " + x.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				MySQLConexion.closeConexion(con);
			} catch (SQLException x) {
				System.out.println("Error al cerrar los recursos: " + x.getMessage());
			}
		}
		return e;
	}

	@Override
	public int actualizar(Empleado e) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update Empleado set nombre_empleado = ?, apellido_empleado = ?, username_empleado = ?, password_empleado = ? where id_empleado = ?;";
			pst = con.prepareStatement(sql);
			pst.setString(1, e.getNombre_empleado());
			pst.setString(2, e.getApellido_empleado());
			pst.setString(3, e.getUsername_empleado());
			pst.setString(4, e.getPassword_empleado());
			pst.setString(5, e.getId_empleado());
			ok = pst.executeUpdate();
		} catch (Exception x) {
			System.out.println("Error en Actualizar Cliente: " + x.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
