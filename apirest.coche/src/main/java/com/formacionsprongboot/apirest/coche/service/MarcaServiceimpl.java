package com.formacionsprongboot.apirest.coche.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.coche.dao.MarcaDao;
import com.formacionsprongboot.apirest.coche.entity.Marca;

@Service
public class MarcaServiceimpl implements MarcaService {

	@Autowired
	private MarcaDao AccesoDb;
	@Override
	public List<Marca> ListarTodosMarcas() {
		// TODO Auto-generated method stub
		return (List<Marca>) AccesoDb.findAll();
	}

	@Override
	public Marca FinById(Long id) {
		// TODO Auto-generated method stub
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Marca save(Marca marca) {
		// TODO Auto-generated method stub
		return AccesoDb.save(marca);
	}

	@Override
	public void Delete(Long id) {
		// TODO Auto-generated method stub
		AccesoDb.deleteById(id);
	}

}
