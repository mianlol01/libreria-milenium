package com.milenium;

import java.util.List;

import com.milenium.model.Cliente;
import com.milenium.model.Empleado;
import com.milenium.repository.RCliente;
import com.milenium.repository.REmpleado;

public class Demo {
	public static void main(String[] args) {
		registrarCliente();
	}
	public static void registrarCliente() {
		RCliente rc = new RCliente();
		Cliente c = new Cliente();
		c.setId_cliente("C0001");
		c.setNombre_cliente("Mik");
		c.setApellido_cliente("Ang");
		c.setCorreo_cliente("snake.1503@hotmail.com");
		c.setUsername_cliente("mianlol");
		c.setPassword_cliente("123456");
		
		rc.registrar(c);
	}
	public static void actualizarCliente() {
		RCliente rc = new RCliente();
		Cliente c = new Cliente();
		c.setId_cliente("C0001");
		c.setNombre_cliente("Miguel");
		c.setApellido_cliente("Angel");
		c.setCorreo_cliente("killer.1503@hotmail.com");
		c.setUsername_cliente("mianlol01");
		c.setPassword_cliente("123456789");
		rc.actualizar(c);
	}
	public static void eliminarCliente() {
		RCliente rc = new RCliente();
		rc.eliminar("C0001");
	}
	public static void listarClientes() {
		RCliente rc = new RCliente();
		List<Cliente> listadoClientes = rc.listadoClientes();
		for (Cliente cliente : listadoClientes) {
			System.out.println(cliente.toString());
		}
	}
	public static void listarEmpleados() {
		REmpleado re = new REmpleado();
		List<Empleado> listado = re.listadoEmpleados();
		for (Empleado empleado : listado) {
			System.out.println(empleado.toString());
		}
	}
	public static void actualizarEmpleado() {
		REmpleado re = new REmpleado();
		Empleado e = new Empleado();
		e.setId_empleado("E0001");
		e.setNombre_empleado("Miguel");
		e.setApellido_empleado("Angel");
		e.setUsername_empleado("mianlol01");
		e.setPassword_empleado("123456789");
		re.actualizar(e);
	}
}
