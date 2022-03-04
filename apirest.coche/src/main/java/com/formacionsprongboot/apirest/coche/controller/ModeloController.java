package com.formacionsprongboot.apirest.coche.controller;

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

import com.formacionsprongboot.apirest.coche.entity.Modelo;
import com.formacionsprongboot.apirest.coche.service.ModeloService;


@RestController
@RequestMapping("/api")
public class ModeloController {
	
	@Autowired
	private ModeloService servicio;
	
	@GetMapping({"/Modelos"})
	public List<Modelo> index()
	{
		return servicio.ListarTodosModelos();
	}
	
	@GetMapping("/Modelo/buscarModelo/{id}")
	public ResponseEntity<?> FinModeloById(@PathVariable Long id)
	{
		Modelo modelo = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			modelo = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(modelo == null)
		{
			response.put("mensaje", "El ID de modelo ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Modelo>(modelo,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Modelo/guardarModelo")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Savemodelo(@RequestBody Modelo modelo)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(modelo);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El modelo ha sido creado con exito!");
		response.put("modelo",modelo);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Modelo/updateModelo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updatemodelo(@RequestBody Modelo modelo, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Modelo modeloUpdate = null;	
		
		modeloUpdate = servicio.FinById(id);
		
		try {
			modeloUpdate.setMarca(modelo.getMarca());
			modeloUpdate.setNombre(modelo.getNombre());
				
			servicio.save(modeloUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el modelo no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(modeloUpdate==null)
		{
			response.put("mensaje", "El ID de modelo ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El modelo ha sido actualizado con exito!");
			response.put("Modelo",modeloUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Modelo/deleteModelo/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteModelo(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Modelo modelo = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			modelo = servicio.FinById(id);
						
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(modelo !=null)
		{
			response.put("mensaje", "¡El modelo ha sido eliminado con exito!");
			response.put("modelo",modelo);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}	

}


