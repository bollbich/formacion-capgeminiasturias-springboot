package com.formacionsprongboot.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionsprongboot.apirest.entity.Cliente;
import com.formacionsprongboot.apirest.service.ClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService servicio;
	
	@GetMapping({"/clientes", "/todos"})
	public List<Cliente> index()
	{
		return servicio.ListarTodsClientes();
	}
	
	/*@GetMapping("/cliente/buscarCliente/{id}")
	public Cliente FinClienteById(@PathVariable Long id)
	{
		return servicio.FinById(id);
	}*/
	
	@GetMapping("/cliente/buscarCliente/{id}")
	public ResponseEntity<?> FinClienteById(@PathVariable Long id)
	{
		Cliente cliente = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = servicio.FinById(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null)
		{
			response.put("mensaje", "El Id de cliente ".concat(id.toString()).concat(" no existe en la base de datos"));
		    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		
	}
	
	/*@PostMapping("/cliente/guardarCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente SaveCliente(@RequestBody Cliente cliente)
	{
		return servicio.save(cliente);
	}*/
	
	
	@PostMapping("/cliente/guardarCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> SaveCliente(@RequestBody Cliente cliente)
	{		
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio.save(cliente);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "¡El cliente ha sido creado con exito!");
		response.put("cliente",cliente);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	/*@PutMapping("/cliente/updateCliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente UpdateCliente(@RequestBody Cliente cliente, @PathVariable Long id)
	{
		Cliente clienteUpdate = servicio.FinById(id);
		
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setApellido(cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreatedAt(cliente.getCreatedAt());
		
		return servicio.save(clienteUpdate);
	}*/
	
	
	@PutMapping("/cliente/updateCliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> UpdateCliente(@RequestBody Cliente cliente, @PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		Cliente clienteUpdate = null;
		
		clienteUpdate = servicio.FinById(id);
		
		if(clienteUpdate==null)
		{
			response.put("mensaje", "El Id de cliente ".concat(id.toString()).concat(" no existe en la base de datos"));
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("mensaje", "¡El cliente ha sido actualizado con exito!");
			response.put("cliente",clienteUpdate);
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}
		
		try {		
			
			if(clienteUpdate!=null)
			{
				clienteUpdate.setNombre(cliente.getNombre());
				clienteUpdate.setApellido(cliente.getApellido());
				clienteUpdate.setEmail(cliente.getEmail());
				clienteUpdate.setTelefono(cliente.getTelefono());
				clienteUpdate.setCreatedAt(cliente.getCreatedAt());
				
				servicio.save(clienteUpdate);
			}
			
			
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la update a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado = new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		
		return resultado;
	}
	
	
	/*@DeleteMapping("/cliente/deleteCliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente DeleteCliente(@PathVariable Long id)
	{		
		Cliente cliente = servicio.FinById(id);
		
		servicio.Delete(id);
		
		return cliente;
				
	}*/
	
	
	@DeleteMapping("/cliente/deleteCliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> DeleteCliente(@PathVariable Long id)
	{		
		
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = null;
		
		ResponseEntity<Map<String, Object>> resultado = null;
		
		try {
			 cliente = servicio.FinById(id);
			
			 servicio.Delete(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar el delete a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
		
			resultado =  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente !=null)
		{
			response.put("mensaje", "¡El cliente ha sido eliminado con exito!");
			response.put("cliente",cliente);
			
			resultado = new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		}				
		
		return resultado;
				
	}
	

}
