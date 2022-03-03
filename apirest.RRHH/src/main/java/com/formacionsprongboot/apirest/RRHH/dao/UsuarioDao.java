package com.formacionsprongboot.apirest.RRHH.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionsprongboot.apirest.RRHH.entity.Usuario;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsuario(String user);

}
