package com.milenium.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.milenium.service.SBoleta;

public class Cart extends ArrayList<DetalleBoleta> {

	private static final long serialVersionUID = 1L;

	public int cantidadArticulos() {
		return this.stream().mapToInt(DetalleBoleta::getCantidad) // Mapea cada DetalleBoleta a su cantidad
				.sum(); // Suma todas las cantidades
	}

	public double total() {
		return this.stream().mapToDouble(DetalleBoleta::getImporte) // Mapea cada DetalleBoleta a su importe
				.sum(); // Suma todos los importes
	}

	public void eliminarPorIdLibro(int id_libro) {
		Iterator<DetalleBoleta> iterator = this.iterator();
		while (iterator.hasNext()) {
			DetalleBoleta detalleBoleta = iterator.next();
			if (detalleBoleta.getId_libro() == id_libro) {
				iterator.remove(); // Eliminar el objeto de la lista usando el iterador
				break; // Salir del ciclo una vez eliminado
			}
		}
	}

	public String totalPagar() {
		return String.format("%.2f", total());
	}

	public void reemplazarDetalleBoleta(int id_libro, int cantidad) {
		for (int i = 0; i < this.size(); i++) {
			DetalleBoleta detalleBoleta = this.get(i);
			if (detalleBoleta.getId_libro() == id_libro) {
				DetalleBoleta nuevoDetalle = SBoleta.generarDetalleBoleta(id_libro, cantidad).getDetalle();
				this.set(i, nuevoDetalle); // Reemplaza el DetalleBoleta actual
				break; // Salir del ciclo una vez reemplazado
			}
		}
	}

	public int agregarCarro(DetalleBoleta db) {
		DetalleBoleta aux = buscarPorIdLibro(db.getId_libro());
		if (aux == null) {
			this.add(db);
			return -1;
		} else {
			int cantidad = aux.getCantidad() + db.getCantidad();
			if (cantidad > db.getStock()) {
				System.out.println("No contamos con stock suficiente");
				return  db.getStock()- aux.getCantidad();
			}
			reemplazarDetalleBoleta(db.getId_libro(), cantidad);
			return -1;
		}
	}

	public DetalleBoleta buscarPorIdLibro(int id_libro) {
		for (DetalleBoleta detalleBoleta : this) {
			if (detalleBoleta.getId_libro() == id_libro) {
				return detalleBoleta; // Devuelve el DetalleBoleta encontrado
			}
		}
		return null; // Retorna null si no se encuentra
	}
}