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

import com.formacionsprongboot.apirest.coche.entity.Marca;
import com.formacionsprongboot.apirest.coche.service.MarcaService;


@RestController
@RequestMapping("/api")
public class MarcaController {
	
	@Autowired
	private MarcaService servicio;
	
	@GetMapping({"/Marcas"})
	public List<Marca> index()
	{
		return servicio.ListarTodosMarcas();
	}
	
	@GetMapping("/Marca/buscarMarca/{id}")
	public ResponseEntity<?> FinMarcaById(@PathVariable Long id)
	{
		Marca marca = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			marca = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(marca == null)
		{
			response.put("mensaje", "El ID de marca ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Marca>(marca,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Marca/guardarMarca")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Savemarca(@RequestBody Marca marca)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(marca);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El marca ha sido creado con exito!");
		response.put("marca",marca);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Marca/updateMarca/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updatemarca(@RequestBody Marca marca, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Marca marcaUpdate = null;	
		
		marcaUpdate = servicio.FinById(id);
		
		try {
			marcaUpdate.setNombre(marca.getNombre());
				
			servicio.save(marcaUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el marca no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(marcaUpdate==null)
		{
			response.put("mensaje", "El ID de marca ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El marca ha sido actualizado con exito!");
			response.put("Marca",marcaUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Marca/deleteMarca/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteMarca(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Marca marca = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			marca = servicio.FinById(id);
						
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(marca !=null)
		{
			response.put("mensaje", "¡El marca ha sido eliminado con exito!");
			response.put("marca",marca);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}	

}


