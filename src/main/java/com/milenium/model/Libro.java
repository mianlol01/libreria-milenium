package com.milenium.model;

public class Libro {
	private int id_libro;
	private String titulo;
	private int id_autor;
	private String sinopsis;
	private int id_categoria;
	private String fecha_publicacion;
	private double precio;
	private int stock;
	private int descuento;

	private String nombre_autor;
	private String nombre_categoria;

	public double precioFinal() {
		return precio - (precio * descuento / 100);
	}

	public String precio() {
		return String.format("%.2f", precio);
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

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(String fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
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

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	@Override
	public String toString() {
		return "Libro [id_libro=" + id_libro + ", titulo=" + titulo + ", id_autor=" + id_autor + ", sinopsis="
				+ sinopsis + ", id_categoria=" + id_categoria + ", fecha_publicacion=" + fecha_publicacion + ", precio="
				+ precio + ", stock=" + stock + ", descuento=" + descuento + ", nombre_autor=" + nombre_autor
				+ ", nombre_categoria=" + nombre_categoria + "]";
	}
}
