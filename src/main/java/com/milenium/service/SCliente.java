package com.milenium.service;

import com.milenium.repository.RCliente;

public class SCliente {
	public String generarIdCliente() {
		String idCliente = "C0001";
		RCliente rc = new RCliente();
		String maxCliente = rc.obtenerMaximoCliente();
		if (maxCliente != null) {
			idCliente = String.format("C%04d", Integer.parseInt(maxCliente) + 1);
		}
		return idCliente;
	}
}
