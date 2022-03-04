package com.formacionsprongboot.apirest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionsprongboot.apirest.dao.UsuarioDao;
import com.formacionsprongboot.apirest.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioDao AccesoDb;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = null;
		
		try {
			usuario = AccesoDb.findByUsername(username);
			
			if(usuario == null)
			{
				logger.error("Error en el login: No existe el usuario "+username+" en el sistema");
				throw new UsernameNotFoundException("Error en el login: No existe el usuario "+username+" en el sistema");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority->logger.info("Role: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(),usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
