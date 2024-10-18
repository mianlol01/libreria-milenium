package com.milenium.interfaces;

import java.util.ArrayList;

import com.milenium.model.Boleta;

public interface IBoleta {
	public String obtenerMaximoBoleta();
	public int registrar(Boleta b);
	public ArrayList<Boleta> obtenerBoletasCliente(String id_cliente);
}
