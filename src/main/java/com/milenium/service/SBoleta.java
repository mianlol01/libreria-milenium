package com.milenium.service;

import java.util.List;
import com.milenium.model.Boleta;
import com.milenium.model.DetalleBoleta;
import com.milenium.model.Libro;
import com.milenium.repository.RBoleta;
import com.milenium.repository.RDetalleBoleta;
import com.milenium.repository.RLibro;

public class SBoleta {
	public List<Boleta> listarBoletasCliente(String id_cliente) {
		List<Boleta> listaBoletas = null;
		RDetalleBoleta rdb = new RDetalleBoleta();
		RBoleta rb = new RBoleta();
		listaBoletas = rb.obtenerBoletasCliente(id_cliente);
		for (Boleta boleta : listaBoletas) {
			List<DetalleBoleta> listaDetalles = rdb.listarDetallePorIdBoleta(boleta.getId_boleta());
			boleta.setListaDetalle(listaDetalles);
		}
		return listaBoletas;
	}

	public String generaNumBoleta() {
		RBoleta rb = new RBoleta();
		String codigo = "B0001";
		String maxBoleta = rb.obtenerMaximoBoleta();
		if (maxBoleta != null) {
			codigo = String.format("B%04d", Integer.parseInt(maxBoleta) + 1);
		}
		return codigo;
	}

	public static ResultadoBoleta generarDetalleBoleta(int id_libro, int cantidad) {
		ResultadoBoleta result = new ResultadoBoleta();
		// obtener libro
		RLibro rl = new RLibro();
		Libro l = rl.obtenerLibro(id_libro);
		DetalleBoleta db = new DetalleBoleta();
		if (cantidad == 0) {
			result.setMensaje(0);
			System.out.println("La cantidad no puede ser 0");
			return result;
		}
		if (cantidad < 0) {
			result.setMensaje(-1);
			System.out.println("La cantidad no puede ser un valor negativo");
			return result;
		}
		if (cantidad > l.getStock()) {
			result.setMensaje(2);
			System.out.println("No contamos con stock suficiente para ese pedido");
			return result;
		}
		double totalSinDescuento = l.getPrecio() * cantidad;
		double descuentoTotal = cantidad * (l.getPrecio() * l.getDescuento() / 100);
		double importe = totalSinDescuento - descuentoTotal;

		db.setId_libro(id_libro);
		db.setTitulo(l.getTitulo());
		db.setPrecio_unitario(l.getPrecio());
		db.setDescuento_unidad(l.getDescuento());
		db.setTotal(totalSinDescuento);
		db.setCantidad(cantidad);
		db.setDescuento_total(descuentoTotal);
		db.setImporte(importe);

		db.setStock(l.getStock());
		result.setMensaje(1);
		result.setDetalle(db);
		return result;
	}

	public static void main(String[] args) {
		ResultadoBoleta result = generarDetalleBoleta(1, 25);
		if (result.getMensaje() == 0) {
			System.out.println("La cantidad no puede ser 0");
			return;
		}
		if (result.getMensaje() == -1) {
			System.out.println("La cantidad no puede ser un valor negativo");
			return;
		}
		if (result.getMensaje() == 2) {
			System.out.println("No contamos con stock suficiente para ese pedido");
			return;
		}
		System.out.println(result.getDetalle().toString());
	}
}
