package com.avla.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avla.prueba.domain.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{

}
