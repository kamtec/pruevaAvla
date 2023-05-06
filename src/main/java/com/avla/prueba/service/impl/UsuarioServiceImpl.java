package com.avla.prueba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avla.prueba.domain.Usuario;
import com.avla.prueba.repository.UsuarioRepository;
import com.avla.prueba.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	public List<Usuario> consultarUsuarios() {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario encontrarUsuarioPorId(Long Id) {
		Optional<Usuario> op = usuarioRepo.findById(Id);
		return op.isPresent()?op.get():new Usuario();
	}

	@Override
	public Optional<Usuario> encontrarUsuarioPorCorreo(String correo) {
		return usuarioRepo.buscarPorCorreo(correo);
	}

	@Override
	public void eliminarUsuario(Long Id) {
		usuarioRepo.deleteById(Id);
		
	}

	@Override
	public Optional<Usuario> encontrarUsuarioPorIdAactualizar(Long Id) {
		return usuarioRepo.findById(Id);
	}


}
