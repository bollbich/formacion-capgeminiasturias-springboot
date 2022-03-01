package com.formacionsprongboot.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@GetMapping("/cliente/buscarCliente/{id}")
	public Cliente FinClienteById(@PathVariable Long id)
	{
		return servicio.FinById(id);
	}
	
	@PostMapping("/cliente/guardarCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente SaveCliente(@RequestBody Cliente cliente)
	{
		return servicio.save(cliente);
	}
	
	@PutMapping("/cliente/updateCliente/{id}")
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
	}
	
	
	@DeleteMapping("/cliente/deleteCliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente DeleteCliente(@PathVariable Long id)
	{		
		Cliente cliente = servicio.FinById(id);
		
		servicio.Delete(id);
		
		return cliente;
				
	}
	

}
