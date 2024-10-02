package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.DetalleBoleta;

public interface IDetalleBoleta {
	public List<DetalleBoleta> listarBoleta(String id); 
	public int registrar(DetalleBoleta b);

}
