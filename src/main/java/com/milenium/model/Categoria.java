package com.milenium.model;

public class Categoria {
	private int id_categoria;
	private String nombre_categoria;
	
	public String nombreCategoria() {
		return this.nombre_categoria.toUpperCase();
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", nombre_categoria=" + nombre_categoria + "]";
	}
}
