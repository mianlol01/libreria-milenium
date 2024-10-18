package com.milenium.model;

import java.util.List;

public class Libro {
	private int id_libro;
	private String titulo;
	private int id_autor;
	private String sinopsis;
	private String fecha_registro;
	private double precio;
	private int stock;
	private int descuento;
	private List<Categoria> categorias;

	private String nombre_autor;

	public double precioFinal() {
		return this.precio - (this.precio * this.descuento / 100);
	}

	public String precio() {
		return String.format("%.2f", precio);
	}

	public String ruta() {
		return String.format("l%04d", this.id_libro);
	}

	public String descuento() {
		return String.format("%.2f", precioFinal());
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId_autor() {
		return id_autor;
	}

	public void setId_autor(int id_autor) {
		this.id_autor = id_autor;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public String getNombre_autor() {
		return nombre_autor;
	}

	public void setNombre_autor(String nombre_autor) {
		this.nombre_autor = nombre_autor;
	}


	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	@Override
	public String toString() {
		return "Libro [id_libro=" + id_libro + ", titulo=" + titulo + ", id_autor=" + id_autor + ", sinopsis="
				+ sinopsis + ", fecha_registro=" + fecha_registro + ", precio=" + precio + ", stock=" + stock
				+ ", descuento=" + descuento + ", nombre_autor=" + nombre_autor + "]";
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
