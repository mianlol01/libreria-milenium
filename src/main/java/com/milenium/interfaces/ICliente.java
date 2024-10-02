package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.Cliente;

public interface ICliente {
	public List<Cliente> listadoClientes();
	public Cliente ingreso(Cliente c);
	public int registrar(Cliente c);
	public int actualizar(Cliente c);
	public int eliminar(String id);
}
