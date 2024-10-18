package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.Libro;

public interface ILibro {
	public List<Libro> listarLibros();

	public Libro obtenerLibro(int id);

	public int registrarLibro(Libro l, int[] idsCategorias);

	public int eliminarLibro(int id);

	public List<Libro> listarDestacados();

	public List<Libro> buscador(String frase);

	public List<Libro> filtrarCategoria(int id);

	public int actualizar(Libro l);

	public int actualizarPrecioDescuento(int id, double price, int discount);

	public int actualizarStock(int id, int stock);

	public List<Libro> librosSimilares(int id);
	
	public int configurarCategoria(int idLibro, int[]categorias);
}
