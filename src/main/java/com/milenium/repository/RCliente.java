package com.milenium.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.milenium.connection.MySQLConexion;
import com.milenium.interfaces.ICliente;
import com.milenium.model.Boleta;
import com.milenium.model.Cliente;
import com.milenium.model.ClienteFrecuente;
import com.milenium.model.DetalleBoleta;

public class RCliente implements ICliente {

	public List<ClienteFrecuente> obtenerClientesFrecuentes() {
		List<ClienteFrecuente> listaClientes = new ArrayList<>();
		String sql = "SELECT c.id_cliente, c.nombre_cliente, c.apellido_cliente, COUNT(b.id_boleta) AS cantidad_compras "
				+ "FROM Cliente c " + "LEFT JOIN Boleta b ON c.id_cliente = b.id_cliente " + "GROUP BY c.id_cliente "
				+ "ORDER BY cantidad_compras DESC;";

		try (Connection con = MySQLConexion.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ClienteFrecuente cliente = new ClienteFrecuente();
				cliente.setIdCliente(rs.getString("id_cliente"));
				cliente.setNombreCliente(rs.getString("nombre_cliente"));
				cliente.setApellidoCliente(rs.getString("apellido_cliente"));
				cliente.setCantidadCompras(rs.getInt("cantidad_compras"));
				listaClientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	public List<Cliente> obtenerClientesConBoletas() {
		List<Cliente> clientes = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "SELECT c.*, b.id_boleta, b.total, b.fecha_boleta " + "FROM Cliente c "
					+ "LEFT JOIN Boleta b ON c.id_cliente = b.id_cliente";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			Map<String, Cliente> clienteMap = new HashMap<>();
			while (rs.next()) {
				String id_cliente = rs.getString("id_cliente");

				// Si el cliente no está en el mapa, lo agregamos
				Cliente cliente = clienteMap.get(id_cliente);
				if (cliente == null) {
					cliente = new Cliente();
					cliente.setId_cliente(id_cliente);
					cliente.setNombre_cliente(rs.getString("nombre_cliente"));
					cliente.setApellido_cliente(rs.getString("apellido_cliente"));
					cliente.setCorreo_cliente(rs.getString("correo_cliente"));
					cliente.setUsername_cliente(rs.getString("username_cliente"));
					cliente.setBoletas(new ArrayList<>());
					clienteMap.put(id_cliente, cliente);
				}

				// Si hay una boleta, la agregamos
				String id_boleta = rs.getString("id_boleta");
				if (id_boleta != null) {
					Boleta boleta = new Boleta();
					boleta.setId_boleta(id_boleta);
					boleta.setTotal(rs.getDouble("total"));
					boleta.setFecha_boleta(rs.getString("fecha_boleta"));
					cliente.getBoletas().add(boleta);
				}
			}

			// Convertir el mapa a lista
			clientes.addAll(clienteMap.values());

		} catch (Exception e) {
			System.out.println("Error en obtener clientes con boletas: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
		}

		return clientes;
	}

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
	public Cliente obtenerCliente(String id) {
		Cliente c = null;
		if (id == null) {
			return c;
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from Cliente where id_cliente = (?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				c = new Cliente();
				c.setId_cliente(rs.getString("id_cliente"));
				c.setNombre_cliente(rs.getString("nombre_cliente"));
				c.setApellido_cliente(rs.getString("apellido_cliente"));
				c.setCorreo_cliente(rs.getString("correo_cliente"));
				c.setUsername_cliente(rs.getString("username_cliente"));
				c.setPassword_cliente(rs.getString("password_cliente"));
			}

		} catch (SQLException e) {
			System.out.println("Error en obtener Cliente: " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				MySQLConexion.closeConexion(con);
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos: " + e.getMessage());
			}
		}
		return c;

	}

	@Override
	public Cliente ingreso(String username, String password) {
		Cliente c = null;
		if (username == null || password == null) {
			return c;
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call ValidarCliente(?, ?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				c = new Cliente();
				c.setId_cliente(rs.getString("id_cliente"));
				c.setNombre_cliente(rs.getString("nombre_cliente"));
				c.setApellido_cliente(rs.getString("apellido_cliente"));
				c.setCorreo_cliente(rs.getString("correo_cliente"));
				c.setUsername_cliente(rs.getString("username_cliente"));
				c.setPassword_cliente(rs.getString("password_cliente"));
			}

		} catch (SQLException e) {
			System.out.println("Error en validar Cliente: " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				MySQLConexion.closeConexion(con);
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos: " + e.getMessage());
			}
		}
		return c;
	}

	@Override
	public int registrar(Cliente c) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into Cliente (id_cliente, nombre_cliente, apellido_cliente, correo_cliente, username_cliente, password_cliente) values (?,?,?,?,?,?)";
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
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				MySQLConexion.closeConexion(con);
			} catch (SQLException e) {
				System.out.println("Error al cerrar los recursos: " + e.getMessage());
			}
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

	@Override
	public String obtenerMaximoCliente() {
		String maxCliente = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select substr(max(id_cliente),2) from Cliente";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				maxCliente = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("Error en obtenerMaximoCliente: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return maxCliente;
	}

	@Override
	public int verificarUsuario(String id_cliente, String username) {
		int comprobacion = 1; // Estado por defecto
		if (username == null) {
			comprobacion = -1; // Usuario nulo
			return comprobacion;
		}

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			// Consulta para verificar el username del cliente específico
			String sql = "SELECT username_cliente FROM Cliente WHERE id_cliente = ? AND username_cliente = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id_cliente); // Establecer el id_cliente
			pst.setString(2, username); // Establecer el username
			rs = pst.executeQuery();

			if (rs.next()) {
				// Si se encuentra el username que coincide con el id_cliente
				comprobacion = 2; // Usuario encontrado y coincide con el id_cliente
				System.out.println(comprobacion);
			} else {
				// Verificar si el username existe para otros clientes
				sql = "SELECT username_cliente FROM Cliente WHERE username_cliente = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, username);
				rs = pst.executeQuery();

				if (rs.next()) {
					comprobacion = 0; // Username ya en uso por otro cliente
					System.out.println(comprobacion);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en comprobar disponibilidad Usuario: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close(); // Cerrar conexión
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return comprobacion; // Retornar el estado final
	}

	@Override
	public int verificarCorreo(String id_cliente, String email) {
		int comprobacion = 1; // Estado por defecto
		if (email == null) {
			comprobacion = -1; // Correo nulo
			return comprobacion;
		}

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();

			// Consulta para verificar el correo del cliente específico
			String sql = "SELECT correo_cliente FROM Cliente WHERE id_cliente = ? AND correo_cliente = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id_cliente); // Establecer el id_cliente
			pst.setString(2, email); // Establecer el email
			rs = pst.executeQuery();

			if (rs.next()) {
				// Si se encuentra el correo que coincide con el id_cliente
				comprobacion = 2; // Correo encontrado y coincide con el id_cliente
				System.out.println(comprobacion);
			} else {
				// Verificar si el correo existe para otros clientes
				sql = "SELECT correo_cliente FROM Cliente WHERE correo_cliente = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, email);
				rs = pst.executeQuery();

				if (rs.next()) {
					comprobacion = 0; // Correo ya en uso por otro cliente
					System.out.println(comprobacion);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en comprobar disponibilidad Correo: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close(); // Cerrar conexión
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return comprobacion; // Retornar el estado final
	}

	public Cliente obtenerClienteConBoletasYDetalles(String id_cliente) {
		Connection con = null;
		PreparedStatement pstCliente = null;
		PreparedStatement pstDetalles = null;
		ResultSet rsCliente = null;
		ResultSet rsDetalles = null;

		Cliente cliente = null;
		try {
			con = MySQLConexion.getConexion();

			// SQL para obtener el cliente y sus boletas
			String sqlCliente = "SELECT c.id_cliente, c.nombre_cliente, c.apellido_cliente, c.correo_cliente, "
					+ "c.username_cliente, b.id_boleta, b.total, b.fecha_boleta "
					+ "FROM Cliente c LEFT JOIN Boleta b ON c.id_cliente = b.id_cliente WHERE c.id_cliente = ?";
			pstCliente = con.prepareStatement(sqlCliente);
			pstCliente.setString(1, id_cliente);
			rsCliente = pstCliente.executeQuery();

			// Crear cliente
			cliente = new Cliente();
			List<Boleta> listaBoletas = new ArrayList<>();

			while (rsCliente.next()) {
				if (cliente.getId_cliente() == null) {
					cliente.setId_cliente(rsCliente.getString("id_cliente"));
					cliente.setNombre_cliente(rsCliente.getString("nombre_cliente"));
					cliente.setApellido_cliente(rsCliente.getString("apellido_cliente"));
					cliente.setCorreo_cliente(rsCliente.getString("correo_cliente"));
					cliente.setUsername_cliente(rsCliente.getString("username_cliente"));
				}

				// Crear boleta
				Boleta boleta = new Boleta();
				boleta.setId_boleta(rsCliente.getString("id_boleta"));
				boleta.setTotal(rsCliente.getDouble("total"));
				boleta.setFecha_boleta(rsCliente.getString("fecha_boleta"));

				// SQL para obtener los detalles de la boleta
				String sqlDetalles = "SELECT id_detalle_boleta, id_boleta, id_libro, titulo, precio_unitario, descuento_unidad, "
						+ "cantidad, precio_total, descuento_total, importe "
						+ "FROM Detalle_Boleta WHERE id_boleta = ?";
				pstDetalles = con.prepareStatement(sqlDetalles);
				pstDetalles.setString(1, boleta.getId_boleta());
				rsDetalles = pstDetalles.executeQuery();

				List<DetalleBoleta> listaDetalles = new ArrayList<>();

				while (rsDetalles.next()) {
					DetalleBoleta detalle = new DetalleBoleta();
					detalle.setId_detalle_boleta(rsDetalles.getInt("id_detalle_boleta"));
					detalle.setId_boleta(rsDetalles.getString("id_boleta"));
					detalle.setId_libro(rsDetalles.getInt("id_libro"));
					detalle.setTitulo(rsDetalles.getString("titulo"));
					detalle.setPrecio_unitario(rsDetalles.getDouble("precio_unitario"));
					detalle.setDescuento_unidad(rsDetalles.getDouble("descuento_unidad"));
					detalle.setCantidad(rsDetalles.getInt("cantidad"));
					detalle.setTotal(rsDetalles.getDouble("precio_total"));
					detalle.setDescuento_total(rsDetalles.getDouble("descuento_total"));
					detalle.setImporte(rsDetalles.getDouble("importe"));

					listaDetalles.add(detalle);
				}

				// Asignar los detalles a la boleta
				boleta.setListaDetalle(listaDetalles);

				// Añadir la boleta a la lista de boletas del cliente
				listaBoletas.add(boleta);
			}

			// Asignar la lista de boletas al cliente
			cliente.setBoletas(listaBoletas);

		} catch (Exception e) {
			System.out.println("Error al obtener cliente con boletas y detalles: " + e.getMessage());
		} finally {
			MySQLConexion.closeConexion(con);
			if (pstCliente != null)
				try {
					pstCliente.close();
				} catch (Exception ignored) {
				}
			if (pstDetalles != null)
				try {
					pstDetalles.close();
				} catch (Exception ignored) {
				}
			if (rsCliente != null)
				try {
					rsCliente.close();
				} catch (Exception ignored) {
				}
			if (rsDetalles != null)
				try {
					rsDetalles.close();
				} catch (Exception ignored) {
				}
		}

		return cliente;
	}

}