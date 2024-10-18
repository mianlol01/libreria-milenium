package com.milenium.model;

public class CategoriaReporte {
	private String nombreCategoria;
	private int cantidadVendida;

	public CategoriaReporte(String nombreCategoria, int cantidadVendida) {
		this.nombreCategoria = nombreCategoria;
		this.cantidadVendida = cantidadVendida;
	}

	// Getters y Setters
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	@Override
	public String toString() {
		return "Categoria: " + nombreCategoria + ", Vendidos: " + cantidadVendida;
	}
}