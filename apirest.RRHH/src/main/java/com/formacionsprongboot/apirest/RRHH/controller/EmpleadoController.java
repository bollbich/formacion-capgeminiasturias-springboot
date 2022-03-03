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

import com.formacionsprongboot.apirest.RRHH.entity.Empleado;
import com.formacionsprongboot.apirest.RRHH.service.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping({"/Empleados", "/todos"})
	public List<Empleado> index()
	{
		return servicio.ListarTodosEmpleados();
	}
	
	@GetMapping("/Empleado/buscarEmpleado/{id}")
	public ResponseEntity<?> FinEmpleadoById(@PathVariable Long id)
	{
		Empleado empleado = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleado = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(empleado == null)
		{
			response.put("mensaje", "El ID de empleado ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
		}
		else
		{
			return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
		}		
	}
	
	
	@PostMapping("/Empleado/guardarEmpleado")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Saveempleado(@RequestBody Empleado empleado)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(empleado);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El empleado ha sido creado con exito!");
		response.put("empleado",empleado);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@PutMapping("/Empleado/updateEmpleado/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> Updateempleado(@RequestBody Empleado empleado, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Empleado empleadoUpdate = null;	
		
		empleadoUpdate = servicio.FinById(id);
		
		try {
			empleadoUpdate.setDNI(empleado.getDNI());
			empleadoUpdate.setNombre(empleado.getNombre());
			empleadoUpdate.setSalario(empleado.getSalario());
			empleadoUpdate.setTelefono(empleado.getTelefono());
			empleadoUpdate.setDepartamento(empleado.getDepartamento());
				
			servicio.save(empleadoUpdate);				
		}
		catch (NullPointerException f) {
					
			response.put("mensaje", "Error el empleado no existe en la base de datos");
			response.put("error", f.getMessage());
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NO_CONTENT);
				
		} catch (DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);						
		}
		
		
		if(empleadoUpdate==null)
		{
			response.put("mensaje", "El ID de empleado ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El empleado ha sido actualizado con exito!");
			response.put("Empleado",empleadoUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}		
		return resultado;
	}
	
	
	@DeleteMapping("/Empleado/deleteEmpleado/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Map<String, Object>> DeleteEmpleado(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Empleado empleado = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			empleado = servicio.FinById(id);
			
			 servicio.Delete(id);
			 
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado !=null)
		{
			response.put("mensaje", "¡El empleado ha sido eliminado con exito!");
			response.put("empleado",empleado);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		return resultado;				
	}

}
