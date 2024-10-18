package com.milenium;

import com.milenium.model.Cart;
import com.milenium.service.SVenta;

public class Demo1 {
	public static void main(String[] args) {
		SVenta sv = new SVenta();
		String codigoCliente = "C0001";
		Cart lista = new Cart();
		//DetalleBoleta d1 = new DetalleBoleta(1, 5);
		//DetalleBoleta d2 = new DetalleBoleta(2, 10);
		//DetalleBoleta d3 = new DetalleBoleta(3, 1);
		//lista.add(d1);
		//lista.add(d2);
		//lista.add(d3);
		int resultado = sv.realizarVenta(codigoCliente, lista);
		if (resultado == -1) {
			System.out.println("Cancelando Transacción");
		} else {
			System.out.println("Transacción realizada con exito");
		}
	}
}