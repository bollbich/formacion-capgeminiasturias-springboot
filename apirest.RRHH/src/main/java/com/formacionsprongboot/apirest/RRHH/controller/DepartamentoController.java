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

import com.formacionsprongboot.apirest.RRHH.entity.Departamento;
import com.formacionsprongboot.apirest.RRHH.service.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService servicio;
	
	@GetMapping({"/Departamentos", "/todos"})
	public List<Departamento> index()
	{
		return servicio.ListarTodosDepartamentos();
	}
	
	@GetMapping("/Departamento/buscarDepartamento/{id}")
	public ResponseEntity<?> FinDepartamentoById(@PathVariable Long id)
	{
		Departamento departamento = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			departamento = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(departamento == null)
		{
			response.put("mensaje", "El ID de departamento ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Departamento>(departamento,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Departamento/guardarDepartamento")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Savedepartamento(@RequestBody Departamento departamento)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(departamento);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El departamento ha sido creado con exito!");
		response.put("departamento",departamento);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Departamento/updateDepartamento/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updatedepartamento(@RequestBody Departamento departamento, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Departamento departamentoUpdate = null;	
		
		departamentoUpdate = servicio.FinById(id);
		
		try {
			departamentoUpdate.setNombre(departamento.getNombre());
			departamentoUpdate.setUbicacion(departamento.getUbicacion());
				
			servicio.save(departamentoUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el departamento no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(departamentoUpdate==null)
		{
			response.put("mensaje", "El ID de departamento ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El departamento ha sido actualizado con exito!");
			response.put("Departamento",departamentoUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Departamento/deleteDepartamento/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteDepartamento(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Departamento departamento = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			departamento = servicio.FinById(id);
						
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento !=null)
		{
			response.put("mensaje", "¡El departamento ha sido eliminado con exito!");
			response.put("departamento",departamento);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}	

}
