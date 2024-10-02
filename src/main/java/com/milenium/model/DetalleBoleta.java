package com.milenium.model;

public class DetalleBoleta {
	private int id_detalle_boleta;
	private String id_boleta;
	private int id_libro;
	private int cantidad;
	private double importe;

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

	@Override
	public String toString() {
		return "DetalleBoleta [id_detalle_boleta=" + id_detalle_boleta + ", id_boleta=" + id_boleta + ", id_libro="
				+ id_libro + ", cantidad=" + cantidad + ", importe=" + importe + "]";
	}
}
