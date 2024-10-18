package com.milenium.model;

import java.util.List;

public class Boleta {
	private String id_boleta;
	private String id_cliente;
	private double total;
	private String fecha_boleta;
	private List<DetalleBoleta> listaDetalle;

	@Override
	public String toString() {
		return "Boleta [id_boleta=" + id_boleta + ", id_cliente=" + id_cliente + ", total=" + total + ", fecha_boleta="
				+ fecha_boleta + "]";
	}

	public String getId_boleta() {
		return id_boleta;
	}

	public void setId_boleta(String id_boleta) {
		this.id_boleta = id_boleta;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFecha_boleta() {
		return fecha_boleta;
	}

	public void setFecha_boleta(String fecha_boleta) {
		this.fecha_boleta = fecha_boleta;
	}

	public List<DetalleBoleta> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<DetalleBoleta> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

}