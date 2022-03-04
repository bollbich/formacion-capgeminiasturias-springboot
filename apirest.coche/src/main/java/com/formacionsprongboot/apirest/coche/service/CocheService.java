package com.formacionsprongboot.apirest.coche.service;

import java.util.List;

import com.formacionsprongboot.apirest.coche.entity.Coche;

public interface CocheService {
	
	public List<Coche> ListarTodosCoches();
	
	public Coche FinById(Long id);
	
	public Coche save(Coche coche);
	
	public void Delete(Long id);

}
