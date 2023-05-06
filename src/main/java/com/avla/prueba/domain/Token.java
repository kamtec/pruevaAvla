package com.avla.prueba.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="token")
public class Token {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idToken;
	
	@Column(name = "token", nullable = false)
	public String token;

}
