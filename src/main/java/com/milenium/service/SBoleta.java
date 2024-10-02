package com.milenium.service;

import com.milenium.repository.RBoleta;

public class SBoleta {
	public String generaNumBoleta() {
		RBoleta rb = new RBoleta();
		String codigo = "B0001";
		String maxBoleta = rb.obtenerMaximoBoleta();
		if (maxBoleta != null) {
			codigo = String.format("B%04d", Integer.parseInt(maxBoleta) + 1);
		}
		return codigo;
	}
}
