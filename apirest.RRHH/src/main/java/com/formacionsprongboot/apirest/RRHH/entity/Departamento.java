package com.formacionsprongboot.apirest.RRHH.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="departamentos")
public class Departamento implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CodDepartamento;
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="nombre", nullable = false, length=50)
	private String nombre;
	
	@Column(name="ubicacion", nullable = false, length=50)
	private String ubicacion;

	public Long getCodDepartamento() {
		return CodDepartamento;
	}

	public void setCodDepartamento(Long codDepartamento) {
		CodDepartamento = codDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
