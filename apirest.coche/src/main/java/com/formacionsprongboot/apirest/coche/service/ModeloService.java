package com.formacionsprongboot.apirest.coche.service;

import java.util.List;

import com.formacionsprongboot.apirest.coche.entity.Modelo;


public interface ModeloService {
	
	public List<Modelo> ListarTodosModelos();
	
	public Modelo FinById(Long id);
	
	public Modelo save(Modelo modelo);
	
	public void Delete(Long id);

}
