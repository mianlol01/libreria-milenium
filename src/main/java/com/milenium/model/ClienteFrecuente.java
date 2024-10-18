package com.milenium.model;

public class ClienteFrecuente {
	private String idCliente;
	private String nombreCliente;
	private String apellidoCliente;
	private int cantidadCompras;

	// Getters y Setters
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public int getCantidadCompras() {
		return cantidadCompras;
	}

	public void setCantidadCompras(int cantidadCompras) {
		this.cantidadCompras = cantidadCompras;
	}
}
