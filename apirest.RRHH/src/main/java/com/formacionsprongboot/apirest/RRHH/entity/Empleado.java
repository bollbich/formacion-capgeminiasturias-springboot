package com.formacionsprongboot.apirest.RRHH.entity;

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
@Table(name="empleados")
public class Empleado implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CodEmpleado;
	
	@Column(name="DNI", nullable = false, unique=true, length=50)
	private String DNI;
	
	@Column(name="nombre", nullable = false, length=50)
	private String nombre;
	
	@Column(name="salario", nullable = false, length=50)
	private String salario;
	
	@Column(name="telefono", nullable = false, length=50)
	private String telefono;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CodDepartamento")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Departamento departamento;	
	
	private static final long serialVersionUID = 1L;

	public Long getCodEmpleado() {
		return CodEmpleado;
	}

	public void setCodEmpleado(Long codEmpleado) {
		CodEmpleado = codEmpleado;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
}
