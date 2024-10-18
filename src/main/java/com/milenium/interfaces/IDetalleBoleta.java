package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.DetalleBoleta;

public interface IDetalleBoleta {
	public List<DetalleBoleta> listarDetallePorIdBoleta(String id_boleta); 
	public int registrar(DetalleBoleta b);

}
