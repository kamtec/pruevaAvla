package com.avla.prueba.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="usuario")
public class Usuario {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idUsuario;
	
	@Column(name = "name", nullable = false, length = 70)
	public String name;
	
	@Email
	@Column(name = "email", nullable = false, length = 70)
	public String email;
	
	@Column(name = "password", nullable = false, length = 70)
	public String password;
	
	@OneToMany(mappedBy = "usuario", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public List<Telefono> phones;
	
}
