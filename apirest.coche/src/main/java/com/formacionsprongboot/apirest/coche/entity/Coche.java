package com.formacionsprongboot.apirest.coche.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="coches")
public class Coche implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CodCoche;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codMarca")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Marca marca;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codModelo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Modelo modelo;
	
	@Column(name="color", nullable = false, length=50)
	private String color;
	
	@Column(name="matricula", nullable = false, length=50)
	private String matricula;
	
	@Column(name="cilindrada", nullable = false, length=50)
	private int cilindrada;
	
	@Column(name="velocidad", nullable = false)
	private int velocidad;
		
	private static final long serialVersionUID = 1L;

	public Long getCodCoche() {
		return CodCoche;
	}

	public void setCodCoche(Long codCoche) {
		CodCoche = codCoche;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	
}
