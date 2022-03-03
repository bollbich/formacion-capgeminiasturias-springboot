package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.RRHH.dao.JefeDao;
import com.formacionsprongboot.apirest.RRHH.entity.Jefe;

@Service
public class JefeServiceImpl implements JefeService {

	@Autowired
	private JefeDao AccesoDb;
	
	@Override
	public List<Jefe> ListarTodosJefes() {
		
		return (List<Jefe>) AccesoDb.findAll();
	}

	@Override
	public Jefe FinById(Long id) {
		 
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Jefe save(Jefe jefe) {
		 
		return AccesoDb.save(jefe);
	}

	@Override
	public void Delete(Long id) {

		AccesoDb.deleteById(id);

	}

}
