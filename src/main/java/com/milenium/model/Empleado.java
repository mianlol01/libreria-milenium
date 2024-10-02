package com.milenium.model;

public class Empleado {
	private String id_empleado;
	private String nombre_empleado;
	private String apellido_empleado;
	private String username_empleado;
	private String password_empleado;

	public String getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getNombre_empleado() {
		return nombre_empleado;
	}

	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = nombre_empleado;
	}

	public String getApellido_empleado() {
		return apellido_empleado;
	}

	public void setApellido_empleado(String apellido_empleado) {
		this.apellido_empleado = apellido_empleado;
	}

	public String getUsername_empleado() {
		return username_empleado;
	}

	public void setUsername_empleado(String username_empleado) {
		this.username_empleado = username_empleado;
	}

	public String getPassword_empleado() {
		return password_empleado;
	}

	public void setPassword_empleado(String password_empleado) {
		this.password_empleado = password_empleado;
	}

	@Override
	public String toString() {
		return "Empleado [id_empleado=" + id_empleado + ", nombre_empleado=" + nombre_empleado + ", apellido_empleado="
				+ apellido_empleado + ", username_empleado=" + username_empleado + ", password_empleado="
				+ password_empleado + "]";
	}

}
