package com.avla.prueba.service;

import java.util.List;
import java.util.Optional;

import com.avla.prueba.domain.Telefono;

public interface TelefonoService {
	
	Telefono agregarTelefono(Telefono telefono);
	
	Telefono actualizarTelefono(Telefono telefono);
	
	List<Telefono> consultarTelefonos();
	
	Telefono encontrarTelefonoPorId(Long Id);
	
	void eliminarTelefono(Long Id);
	
	Optional<Telefono> encontrarTelefonoPorIdParaActualizar(Long Id);
	

}
