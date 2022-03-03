package com.formacionsprongboot.apirest.RRHH.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CodUsuario;
		
	@Column(name="usuario", nullable = false, unique=true, length=50)
	private String usuario;
	
	@Column(name="password", nullable = false, length=50)
	private String password;
	
	private static final long serialVersionUID = 1L;

	public Long getCodUsuario() {
		return CodUsuario;
	}

	public void setCodUsuario(Long codUsuario) {
		CodUsuario = codUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
