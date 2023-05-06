package com.avla.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avla.prueba.domain.Telefono;

public interface TelefonoRepository  extends JpaRepository<Telefono, Long> {

}
