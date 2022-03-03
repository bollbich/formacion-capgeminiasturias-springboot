package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import com.formacionsprongboot.apirest.RRHH.entity.Empleado;


public interface EmpleadoService {

	public List<Empleado> ListarTodosEmpleados();
	
	public Empleado FinById(Long id);
	
	public Empleado save(Empleado empleado);
	
	public void Delete(Long id);
}
