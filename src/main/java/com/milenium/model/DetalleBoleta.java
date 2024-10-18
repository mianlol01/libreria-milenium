package com.milenium.model;

import java.io.Serializable;

public class DetalleBoleta implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_detalle_boleta;
	private String id_boleta;
	private int id_libro;
	private String titulo;
	private double precio_unitario;
	private double descuento_unidad;
	private double total;
	private double descuento_total;
	private int cantidad;
	private double importe;
	// auxiliares
	private int stock;

	public String precio_unitario() {
		return String.format("%.2f", precio_unitario);
	}

	public String precio_unitarioConDescuento() {
		double p = precio_unitario - (precio_unitario * (descuento_unidad / 100));
		return String.format("%.2f", p);
	}

	public String ruta() {
		return String.format("l%04d", this.id_libro);
	}

	public String importe() {
		return String.format("%.2f", importe);
	}

	public int getId_detalle_boleta() {
		return id_detalle_boleta;
	}

	public void setId_detalle_boleta(int id_detalle_boleta) {
		this.id_detalle_boleta = id_detalle_boleta;
	}

	public String getId_boleta() {
		return id_boleta;
	}

	public void setId_boleta(String id_boleta) {
		this.id_boleta = id_boleta;
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

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public double getDescuento_unidad() {
		return descuento_unidad;
	}

	public void setDescuento_unidad(double descuento_unidad) {
		this.descuento_unidad = descuento_unidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDescuento_total() {
		return descuento_total;
	}

	public void setDescuento_total(double descuento_total) {
		this.descuento_total = descuento_total;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "DetalleBoleta [id_boleta=" + id_boleta + ", titulo=" + titulo + ", precio_unitario=" + precio_unitario
				+ ", descuento_unidad=" + descuento_unidad + ", total=" + total + ", descuento_total=" + descuento_total
				+ ", cantidad=" + cantidad + ", importe=" + importe + "]";
	}

}
