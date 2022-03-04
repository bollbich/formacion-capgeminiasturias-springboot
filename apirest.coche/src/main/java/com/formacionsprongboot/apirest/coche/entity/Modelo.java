package com.formacionsprongboot.apirest.coche.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="modelo")
public class Modelo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CodModelo;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codMarca")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Marca marca;
	
	@Column(name="nombre", nullable = false, length=50)
	private String nombre;

	public Long getCodModelo() {
		return CodModelo;
	}

	public void setCodModelo(Long codModelo) {
		CodModelo = codModelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
