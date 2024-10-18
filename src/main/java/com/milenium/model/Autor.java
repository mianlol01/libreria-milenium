package com.milenium.model;

public class Autor {
	private int id_autor;
	private String nombre_autor;
	private int cantidad_libros;

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

	public int getCantidad_libros() {
		return cantidad_libros;
	}

	public void setCantidad_libros(int cantidad_libros) {
		this.cantidad_libros = cantidad_libros;
	}
}
