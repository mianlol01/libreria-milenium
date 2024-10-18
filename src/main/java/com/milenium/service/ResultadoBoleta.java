package com.milenium.service;

import com.milenium.model.DetalleBoleta;

public class ResultadoBoleta {
	private DetalleBoleta detalle; // Objeto de la clase Libro
    private int mensaje;
	public int getMensaje() {
		return mensaje;
	}
	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}
	public DetalleBoleta getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleBoleta detalle) {
		this.detalle = detalle;
	}
}
