package com.formacionsprongboot.apirest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre", nullable = false, length=50)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length=50)
	private String apellido;
	
	@Column(name="email", nullable = false, length=50)
	private String email;
	
	@Column(name="telefono", nullable = false, length=50)
	private int telefono;
	
	@Column(name="created_At")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@PrePersist
	public void prePersist()
	{
		if(createdAt==null)
		this.setCreatedAt(new Date());
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
