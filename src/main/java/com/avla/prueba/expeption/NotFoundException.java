package com.avla.prueba.expeption;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException(String mensaje) {
		super(mensaje);
	}

}
