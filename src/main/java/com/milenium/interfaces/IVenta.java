package com.milenium.interfaces;

import java.util.ArrayList;

import com.milenium.model.Boleta;
import com.milenium.model.DetalleBoleta;

public interface IVenta {
	public String generaNumBoleta();
	public int realizarVenta(Boleta b, ArrayList<DetalleBoleta> d);
}
