package com.formacionsprongboot.apirest.RRHH.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionsprongboot.apirest.RRHH.entity.Usuario;
import com.formacionsprongboot.apirest.RRHH.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioService servicio;
	
	@GetMapping({"/Usuarios", "/todos"})
	public List<Usuario> index()
	{
		return servicio.ListarTodosUsuarios();
	}
	
	@GetMapping("/Usuario/buscarUsuario/{id}")
	public ResponseEntity<?> FinUsuarioById(@PathVariable Long id)
	{
		Usuario usuario = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(usuario == null)
		{
			response.put("mensaje", "El ID de usuario ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Usuario/guardarUsuario")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Saveusuario(@RequestBody Usuario usuario)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(usuario);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El usuario ha sido creado con exito!");
		response.put("usuario",usuario);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Usuario/updateUsuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updateusuario(@RequestBody Usuario usuario, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Usuario usuarioUpdate = null;	
		
		usuarioUpdate = servicio.FinById(id);
		
		try {
			usuarioUpdate.setPassword(usuario.getPassword());
			usuarioUpdate.setUsuario(usuario.getUsuario());
				
			servicio.save(usuarioUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el usuario no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(usuarioUpdate==null)
		{
			response.put("mensaje", "El ID de usuario ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El usuario ha sido actualizado con exito!");
			response.put("Usuario",usuarioUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Usuario/deleteUsuario/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteUsuario(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Usuario usuario = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			usuario = servicio.FinById(id);
			
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario !=null)
		{
			response.put("mensaje", "¡El usuario ha sido eliminado con exito!");
			response.put("usuario",usuario);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}


}
