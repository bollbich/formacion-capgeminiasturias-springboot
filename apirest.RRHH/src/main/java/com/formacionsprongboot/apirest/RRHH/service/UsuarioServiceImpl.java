package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionsprongboot.apirest.RRHH.dao.UsuarioDao;
import com.formacionsprongboot.apirest.RRHH.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao AccesoDb;
	
	@Override
	public List<Usuario> ListarTodosUsuarios() {
 
		return (List<Usuario>) AccesoDb.findAll();
	}

	@Override
	public Usuario FinById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Usuario save(Usuario usuario) {
		 
		return AccesoDb.save(usuario);
	}

	@Override
	public void Delete(Long id) {

		AccesoDb.deleteById(id);

	}

}
