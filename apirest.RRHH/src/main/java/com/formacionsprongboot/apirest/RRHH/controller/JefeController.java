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

import com.formacionsprongboot.apirest.RRHH.entity.Jefe;
import com.formacionsprongboot.apirest.RRHH.service.JefeService;

@RestController
@RequestMapping("/api")
public class JefeController {
	
	@Autowired
	private JefeService servicio;
	
	@GetMapping({"/Jefes", "/todos"})
	public List<Jefe> index()
	{
		return servicio.ListarTodosJefes();
	}
	
	@GetMapping("/Jefe/buscarJefe/{id}")
	public ResponseEntity<?> FinJefeById(@PathVariable Long id)
	{
		Jefe jefe = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			jefe = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(jefe == null)
		{
			response.put("mensaje", "El ID de jefe ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Jefe>(jefe,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Jefe/guardarJefe")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Savejefe(@RequestBody Jefe jefe)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(jefe);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El jefe ha sido creado con exito!");
		response.put("jefe",jefe);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Jefe/updateJefe/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updatejefe(@RequestBody Jefe jefe, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Jefe jefeUpdate = null;	
		
		jefeUpdate = servicio.FinById(id);
		
		try {
			jefeUpdate.setDNI(jefe.getDNI());
			jefeUpdate.setNombre(jefe.getNombre());
			jefeUpdate.setSalario(jefe.getSalario());
			jefeUpdate.setTelefono(jefe.getTelefono());
			jefeUpdate.setDepartamento(jefe.getDepartamento());
				
			servicio.save(jefeUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el jefe no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(jefeUpdate==null)
		{
			response.put("mensaje", "El ID de jefe ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El jefe ha sido actualizado con exito!");
			response.put("Jefe",jefeUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Jefe/deleteJefe/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteJefe(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Jefe jefe = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			jefe = servicio.FinById(id);
			
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(jefe !=null)
		{
			response.put("mensaje", "¡El jefe ha sido eliminado con exito!");
			response.put("jefe",jefe);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}

}
