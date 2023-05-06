package com.avla.prueba.service;

import java.util.List;
import java.util.Optional;

import com.avla.prueba.domain.Usuario;

public interface UsuarioService {

    Usuario agregarUsuario(Usuario usuario);
	
    Usuario actualizarUsuario(Usuario usuario);
	
	List<Usuario> consultarUsuarios();
	
	Usuario encontrarUsuarioPorId(Long Id);
	
	Optional<Usuario> encontrarUsuarioPorCorreo(String correo);
	
	Optional<Usuario> encontrarUsuarioPorIdAactualizar(Long Id);
	
	void eliminarUsuario(Long Id);
}
