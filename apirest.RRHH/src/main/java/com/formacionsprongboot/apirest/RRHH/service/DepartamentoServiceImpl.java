package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.RRHH.dao.DepartamentoDao;
import com.formacionsprongboot.apirest.RRHH.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	DepartamentoDao AccesoDb;
	
	@Override
	public List<Departamento> ListarTodosDepartamentos() {
		
		return (List<Departamento>) AccesoDb.findAll();
	}

	@Override
	public Departamento FinById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Departamento save(Departamento departamento) {
		
		return AccesoDb.save(departamento);
	}

	@Override
	public void Delete(Long id) {

		AccesoDb.deleteById(id);

	}

}
