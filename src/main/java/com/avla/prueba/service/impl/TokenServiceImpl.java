package com.avla.prueba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avla.prueba.domain.Token;
import com.avla.prueba.repository.TokenRepository;
import com.avla.prueba.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{
         
	@Autowired
	private TokenRepository tokenRepo;

	@Override
	public Token agregarToken(Token token) {
		return tokenRepo.save(token);
	}

	@Override
	public Token actualizarToken(Token token) {
		return tokenRepo.save(token);
	}

	@Override
	public List<Token> consultarTokens() {
		return tokenRepo.findAll();
	}

	@Override
	public Token encontrarTokenPorId(Long Id) {
		Optional<Token> op = tokenRepo.findById(Id);
		return op.isPresent()?op.get():new Token();
	}

	@Override
	public void eliminarToken(Long Id) {
		tokenRepo.deleteById(Id);
		
	}
}
