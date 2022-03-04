package com.formacionsprongboot.apirest.coche.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marcas")
public class Marca implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CodMarca;
	
	@Column(name="nombre", nullable = false, length=50)
	private String nombre;

	public Long getCodMarca() {
		return CodMarca;
	}

	public void setCodMarca(Long codMarca) {
		CodMarca = codMarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
