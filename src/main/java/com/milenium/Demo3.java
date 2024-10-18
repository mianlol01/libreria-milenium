package com.milenium;

import java.util.List;

import com.milenium.model.Boleta;
import com.milenium.model.DetalleBoleta;
import com.milenium.service.SBoleta;

public class Demo3 {
	public static void main(String[] args) {
		SBoleta sb = new SBoleta();
		List<Boleta> listaBoletas = sb.listarBoletasCliente("C0001");
		for (Boleta boleta : listaBoletas) {
			System.out.println(boleta.toString());
			List<DetalleBoleta> listaDetalleBoletas = boleta.getListaDetalle();
			for (DetalleBoleta detalleBoleta : listaDetalleBoletas) {
				System.out.println(detalleBoleta.toString());
			}
		}
	}
}