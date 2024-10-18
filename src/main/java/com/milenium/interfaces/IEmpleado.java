package com.milenium.interfaces;

import java.util.List;

import com.milenium.model.Empleado;

public interface IEmpleado {
	public List<Empleado> listadoEmpleados();

	public Empleado ingreso(String username, String password);

	public int actualizar(Empleado e);
}
