package com.formacionsprongboot.apirest.RRHH.service;

import java.util.List;

import com.formacionsprongboot.apirest.RRHH.entity.Usuario;


public interface UsuarioService {

	public List<Usuario> ListarTodosUsuarios();
	
	public Usuario FinById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void Delete(Long id);
	
	public Usuario findByUsuario(String user);
	
}
