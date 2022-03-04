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

import com.formacionsprongboot.apirest.coche.entity.Coche;
import com.formacionsprongboot.apirest.coche.service.CocheService;

@RestController
@RequestMapping("/api")
public class CocheController {
	
	@Autowired
	private CocheService servicio;
	
	@GetMapping({"/Coches"})
	public List<Coche> index()
	{
		return servicio.ListarTodosCoches();
	}
	
	@GetMapping("/Coche/buscarCoche/{id}")
	public ResponseEntity<?> FinCocheById(@PathVariable Long id)
	{
		Coche coche = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			coche = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(coche == null)
		{
			response.put("mensaje", "El ID de coche ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Coche>(coche,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Coche/guardarCoche")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Savecoche(@RequestBody Coche coche)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(coche);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El coche ha sido creado con exito!");
		response.put("coche",coche);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Coche/updateCoche/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updatecoche(@RequestBody Coche coche, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Coche cocheUpdate = null;	
		
		cocheUpdate = servicio.FinById(id);
		
		try {
			cocheUpdate.setMarca(coche.getMarca());
			cocheUpdate.setModelo(coche.getModelo());
			cocheUpdate.setColor(coche.getColor());
			cocheUpdate.setMatricula(coche.getMatricula());
			cocheUpdate.setCilindrada(coche.getCilindrada());
			cocheUpdate.setVelocidad(coche.getVelocidad());
				
			servicio.save(cocheUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el coche no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(cocheUpdate==null)
		{
			response.put("mensaje", "El ID de coche ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El coche ha sido actualizado con exito!");
			response.put("Coche",cocheUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Coche/deleteCoche/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteCoche(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Coche coche = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			coche = servicio.FinById(id);
						
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(coche !=null)
		{
			response.put("mensaje", "¡El coche ha sido eliminado con exito!");
			response.put("coche",coche);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}	

}

