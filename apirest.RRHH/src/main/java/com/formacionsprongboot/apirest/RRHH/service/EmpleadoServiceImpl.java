package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.RRHH.dao.EmpleadoDao;
import com.formacionsprongboot.apirest.RRHH.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	EmpleadoDao AccesoDb;
	
	@Override
	public List<Empleado> ListarTodosEmpleados() {
		
		return (List<Empleado>) AccesoDb.findAll();
	}

	@Override
	public Empleado FinById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Empleado save(Empleado empleado) {
		
		return AccesoDb.save(empleado);
	}

	@Override
	public void Delete(Long id) {
		
		AccesoDb.deleteById(id);

	}

}
