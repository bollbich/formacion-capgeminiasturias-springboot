package com.formacionsprongboot.apirest.coche.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.coche.dao.ModeloDao;
import com.formacionsprongboot.apirest.coche.entity.Modelo;

@Service
public class ModeloServiceImpl implements ModeloService {

	@Autowired
	private ModeloDao AccesoDb;
	
	@Override
	public List<Modelo> ListarTodosModelos() {
		// TODO Auto-generated method stub
		return (List<Modelo>) AccesoDb.findAll();
	}

	@Override
	public Modelo FinById(Long id) {
		// TODO Auto-generated method stub
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Modelo save(Modelo modelo) {
		// TODO Auto-generated method stub
		return AccesoDb.save(modelo);
	}

	@Override
	public void Delete(Long id) {
		// TODO Auto-generated method stub
		AccesoDb.deleteById(id);

	}

}
