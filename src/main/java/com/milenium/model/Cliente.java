package com.milenium.model;

import java.util.List;

public class Cliente {
	private String id_cliente;
	private String nombre_cliente;
	private String apellido_cliente;
	private String correo_cliente;
	private String username_cliente;
	private String password_cliente;
	private List<Boleta> boletas;

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_cliente() {
		return apellido_cliente;
	}

	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}

	public String getCorreo_cliente() {
		return correo_cliente;
	}

	public void setCorreo_cliente(String correo_cliente) {
		this.correo_cliente = correo_cliente;
	}

	public String getUsername_cliente() {
		return username_cliente;
	}

	public void setUsername_cliente(String username_cliente) {
		this.username_cliente = username_cliente;
	}

	public String getPassword_cliente() {
		return password_cliente;
	}

	public void setPassword_cliente(String password_cliente) {
		this.password_cliente = password_cliente;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nombre_cliente=" + nombre_cliente + ", apellido_cliente="
				+ apellido_cliente + ", correo_cliente=" + correo_cliente + ", username_cliente=" + username_cliente
				+ ", password_cliente=" + password_cliente + "]";
	}

	public List<Boleta> getBoletas() {
		return boletas;
	}

	public void setBoletas(List<Boleta> boletas) {
		this.boletas = boletas;
	}
}
