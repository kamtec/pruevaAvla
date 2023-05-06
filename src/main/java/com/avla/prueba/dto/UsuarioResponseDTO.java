package com.avla.prueba.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioResponseDTO {
          
	public Long id;
	
	public LocalDateTime created;
	
	public LocalDateTime modified;
	
	public String token;
	  
}
