package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import com.formacionsprongboot.apirest.RRHH.entity.Jefe;

public interface JefeService {
	
	public List<Jefe> ListarTodosJefes();
	
	public Jefe FinById(Long id);
	
	public Jefe save(Jefe jefe);
	
	public void Delete(Long id);

}
