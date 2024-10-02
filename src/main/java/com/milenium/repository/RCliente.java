package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.ICliente;
import com.milenium.model.Cliente;

public class RCliente implements ICliente {

	@Override
	public List<Cliente> listadoClientes() {
		List<Cliente> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Cliente";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			lista = new ArrayList<Cliente>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId_cliente(rs.getString("id_cliente"));
				c.setNombre_cliente(rs.getString("nombre_cliente"));
				c.setApellido_cliente(rs.getString("apellido_cliente"));
				c.setCorreo_cliente(rs.getString("correo_cliente"));
				c.setUsername_cliente(rs.getString("username_cliente"));
				c.setPassword_cliente(rs.getString("password_cliente"));
				lista.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en Listar Clientes: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return lista;
	}

	@Override
	public Cliente ingreso(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registrar(Cliente c) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert Cliente (id_cliente, nombre_cliente, apellido_cliente, correo_cliente, username_cliente, password_cliente) values (?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getId_cliente());
			pst.setString(2, c.getNombre_cliente());
			pst.setString(3, c.getApellido_cliente());
			pst.setString(4, c.getCorreo_cliente());
			pst.setString(5, c.getUsername_cliente());
			pst.setString(6, c.getPassword_cliente());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en registrar Cliente: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public int actualizar(Cliente c) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update Cliente set nombre_cliente = ?, apellido_cliente = ?, correo_cliente = ?, username_cliente = ?, password_cliente = ? where id_cliente = ?;";
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getNombre_cliente());
			pst.setString(2, c.getApellido_cliente());
			pst.setString(3, c.getCorreo_cliente());
			pst.setString(4, c.getUsername_cliente());
			pst.setString(5, c.getPassword_cliente());
			pst.setString(6, c.getId_cliente());
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en Actualizar Cliente: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

	@Override
	public int eliminar(String id) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar Cliente: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}
		return ok;
	}

}
