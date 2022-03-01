package com.formacionsprongboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionsprongboot.apirest.dao.ClienteDao;
import com.formacionsprongboot.apirest.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao AccesoDb;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> ListarTodsClientes() {
		
		return (List<Cliente>) AccesoDb.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente FinById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		
		return AccesoDb.save(cliente);
	}

	@Override
	@Transactional
	public void Delete(Long id) {
				
		AccesoDb.deleteById(id);
	}

}
