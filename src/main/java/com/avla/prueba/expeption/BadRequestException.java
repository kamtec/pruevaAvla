package com.avla.prueba.expeption;

public class BadRequestException extends RuntimeException {
	
	public BadRequestException(String mensaje) {
		super(mensaje);
	}

}
