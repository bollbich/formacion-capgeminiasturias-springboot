package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import com.formacionsprongboot.apirest.RRHH.entity.Departamento;

public interface DepartamentoService {

	public List<Departamento> ListarTodosDepartamentos();
	
	public Departamento FinById(Long id);
	
	public Departamento save(Departamento departamento);
	
	public void Delete(Long id);
}
