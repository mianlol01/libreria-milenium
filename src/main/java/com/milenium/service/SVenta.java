package com.milenium.service;

import com.milenium.model.Boleta;
import com.milenium.model.Cart;
import com.milenium.model.DetalleBoleta;
import com.milenium.model.Libro;
import com.milenium.repository.RBoleta;
import com.milenium.repository.RDetalleBoleta;
import com.milenium.repository.RLibro;

public class SVenta {
	public String generaNumBoleta() {
		RBoleta rb = new RBoleta();
		String codigo = "B0001";
		String maxBoleta = rb.obtenerMaximoBoleta();
		if (maxBoleta != null) {
			codigo = String.format("B%04d", Integer.parseInt(maxBoleta) + 1);
		}
		return codigo;
	}

	public int realizarVenta(String codigoCliente, Cart listaBoleta) {
		RDetalleBoleta rdb = new RDetalleBoleta();
		RBoleta rb = new RBoleta();
		RLibro rl = new RLibro();

		// Crear boleta vacía
		Boleta b = new Boleta();
		b.setId_boleta(generaNumBoleta());
		b.setId_cliente(codigoCliente);

		for (DetalleBoleta detalleBoleta : listaBoleta) {
			Libro l = rl.obtenerLibro(detalleBoleta.getId_libro());
			if (l.getStock() < detalleBoleta.getCantidad()) {
				System.out.println("No existe stock suficiente para : " + l.getTitulo());
				System.out.println("Cantidad solicitada : " + detalleBoleta.getCantidad());
				System.out.println("Cantidad disponible : " + l.getStock());
				return -1;
			}
			double importe = ((l.getPrecio() - (l.getPrecio() * l.getDescuento() / 100)) * detalleBoleta.getCantidad());
			detalleBoleta.setId_boleta(b.getId_boleta());
			detalleBoleta.setImporte(importe);
			b.setTotal(b.getTotal() + importe);
		}
		// Registrar boleta
		rb.registrar(b);
		// Registrar detalles de la boleta
		for (DetalleBoleta detalleBoleta : listaBoleta) {
			rdb.registrar(detalleBoleta);
		}
		// Actualizar Stock
		for (DetalleBoleta detalleBoleta : listaBoleta) {
			Libro l = rl.obtenerLibro(detalleBoleta.getId_libro());
			rl.actualizarStock(l.getId_libro(), detalleBoleta.getCantidad());
		}
		return 1;
	}
}