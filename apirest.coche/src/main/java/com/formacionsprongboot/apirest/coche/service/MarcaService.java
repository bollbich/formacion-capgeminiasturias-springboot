package com.formacionsprongboot.apirest.coche.service;

import java.util.List;

import com.formacionsprongboot.apirest.coche.entity.Marca;

public interface MarcaService {
	
	public List<Marca> ListarTodosMarcas();
	
	public Marca FinById(Long id);
	
	public Marca save(Marca marca);
	
	public void Delete(Long id);

}
