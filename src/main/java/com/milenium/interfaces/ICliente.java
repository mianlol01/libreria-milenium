package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.Cliente;

public interface ICliente {
	public List<Cliente> obtenerClientesConBoletas();
	public Cliente obtenerCliente(String id);
	public List<Cliente> listadoClientes();
	public Cliente ingreso(String username, String password);
	public int registrar(Cliente c);
	public int actualizar(Cliente c);
	public int eliminar(String id);
	public String obtenerMaximoCliente();
	public int verificarUsuario(String id_cliente ,String username);
	public int verificarCorreo(String id_cliente, String email);
	public Cliente obtenerClienteConBoletasYDetalles(String id_cliente);
}
