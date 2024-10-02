package com.milenium.model;

public class Autor {
	private int id_autor;
	private String nombre_autor;

	public int getId_autor() {
		return id_autor;
	}

	public void setId_autor(int id_autor) {
		this.id_autor = id_autor;
	}

	public String getNombre_autor() {
		return nombre_autor;
	}

	public void setNombre_autor(String nombre_autor) {
		this.nombre_autor = nombre_autor;
	}

	@Override
	public String toString() {
		return "Autor [id_autor=" + id_autor + ", nombre_autor=" + nombre_autor + "]";
	}
}
