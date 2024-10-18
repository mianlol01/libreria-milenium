package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.Autor;

public interface IAutor {
	public List<Autor> listarAutores();
	public Autor obtenerAutor(int id);
	public int registrarAutor(Autor a);
	public int eliminarAutor(int id);
	public Autor obtenerAutor(String nombre_autor);
}
