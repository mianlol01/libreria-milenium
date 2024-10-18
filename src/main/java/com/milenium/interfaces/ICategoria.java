package com.milenium.interfaces;

import java.util.List;
import com.milenium.model.Categoria;
import com.milenium.model.CategoriaReporte;

public interface ICategoria {
	public List<Categoria> listarCategoria();
	List<CategoriaReporte> obtenerReporteCategoriasVendidas();
	public Categoria obtenerCategoria(int id);
}
