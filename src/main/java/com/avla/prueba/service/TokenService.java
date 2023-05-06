package com.avla.prueba.service;

import java.util.List;

import com.avla.prueba.domain.Token;

public interface TokenService {
	
    Token agregarToken(Token token);
	
	Token actualizarToken(Token token);
	
	List<Token> consultarTokens();
	
	Token encontrarTokenPorId(Long Id);
	
	void eliminarToken(Long Id);
}
