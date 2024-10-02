package com.milenium.interfaces;

import java.util.List;
import com.milenium.model.Categoria;

public interface ICategoria {
	public List<Categoria> listarCategoria();

	public Categoria obtenerCategoria(int id);
}
