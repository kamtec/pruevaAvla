package com.avla.prueba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avla.prueba.domain.Telefono;
import com.avla.prueba.repository.TelefonoRepository;
import com.avla.prueba.service.TelefonoService;

@Service
public class TelefonoServiceImpl implements TelefonoService{
	
	@Autowired
	private TelefonoRepository telefonoRepo;

	@Override
	public Telefono agregarTelefono(Telefono telefono) {
		return telefonoRepo.save(telefono);
	}

	@Override
	public Telefono actualizarTelefono(Telefono telefono) {
		return telefonoRepo.save(telefono);
	}

	@Override
	public List<Telefono> consultarTelefonos() {
		return telefonoRepo.findAll();
	}

	@Override
	public Telefono encontrarTelefonoPorId(Long Id) {
		Optional<Telefono> op = telefonoRepo.findById(Id);
		return op.isPresent()?op.get():new Telefono();
	}

	@Override
	public void eliminarTelefono(Long Id) {
	telefonoRepo.deleteById(Id);
	}

}
