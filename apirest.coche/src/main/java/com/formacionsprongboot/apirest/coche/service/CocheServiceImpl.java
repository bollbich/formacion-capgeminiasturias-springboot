package com.formacionsprongboot.apirest.coche.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.coche.dao.CocheDao;
import com.formacionsprongboot.apirest.coche.entity.Coche;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheDao AccesoDb;
	
	@Override
	public List<Coche> ListarTodosCoches() {
		// TODO Auto-generated method stub
		return (List<Coche>) AccesoDb.findAll();
	}

	@Override
	public Coche FinById(Long id) {
		// TODO Auto-generated method stub
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Coche save(Coche coche) {
		// TODO Auto-generated method stub
		return AccesoDb.save(coche);
	}

	@Override
	public void Delete(Long id) {
		// TODO Auto-generated method stub
		AccesoDb.deleteById(id);

	}

}
