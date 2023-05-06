package com.avla.prueba.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avla.prueba.domain.Token;
import com.avla.prueba.domain.Usuario;
import com.avla.prueba.dto.UsuarioResponseDTO;
import com.avla.prueba.service.TokenService;
import com.avla.prueba.service.UsuarioService;
import com.avla.prueba.utils.JwtUtils;
import com.avla.prueba.utils.ValidarPasswordUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuario")
@Api(value = "API de generacion de usuarios", description = "Esta es una API de prueba de generacion de usuarios que ademas genera tokens")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private TokenService tService;
	
	@Autowired
	private JwtUtils jwt;
	
	@Autowired
	private ValidarPasswordUtils validarPass;
	
	@PostMapping("/agregar")
	@ApiOperation(value = "Agregar un usuario", notes = "Agrega un usuario y genera token a partir de el")
	public ResponseEntity<UsuarioResponseDTO> agregarUsuario(@ApiParam(value = "informacion del usuario, esta es email, contraseña, nombre y telefonos", required = true)  @Valid @RequestBody Usuario usuario){
		if(!validarPass.validate(usuario.getPassword())) {
			throw new IllegalArgumentException("El fromato de la contraseña no se valido");
		}else {
		Optional<Usuario> usuarioEncontrado = service.encontrarUsuarioPorCorreo(usuario.getEmail());
		if(!usuarioEncontrado.isPresent()) {
		log.info("creando usuario");
		Usuario us = service.agregarUsuario(usuario);
		log.info("usuario creado con el id "+us.getIdUsuario());
		UsuarioResponseDTO usuarioCreadoDTO = new UsuarioResponseDTO();
		log.info("generando respuesta con token");
		usuarioCreadoDTO.setId(us.getIdUsuario());
		usuarioCreadoDTO.setCreated(LocalDateTime.now());
		usuarioCreadoDTO.setToken(jwt.generateToken(us.getEmail()));
		log.info("token generado");
		Token token = new Token();
		token.setToken(usuarioCreadoDTO.getToken());
		tService.agregarToken(token);
		log.info("token guardado");
		return new ResponseEntity<UsuarioResponseDTO>(usuarioCreadoDTO, HttpStatus.OK);
		}else {
		throw new IllegalArgumentException("El correo ya se encuentra registrado.");
		}
		}
	}
	
	@PutMapping("/actualizar")
	@ApiOperation(value = "Actualiza un usuario", notes = "Actualiza un usuario y genera token a partir de el")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@ApiParam(value = "informacion del usuario, esta es email, contraseña, nombre y telefonos", required = true)  @Valid @RequestBody Usuario usuario){
	Optional<Usuario> usuarioEncontradoPorId = service.encontrarUsuarioPorIdAactualizar(usuario.getIdUsuario());
		if(usuarioEncontradoPorId.isPresent()) {
	    log.info("actualizando usuario");
		Usuario us =service.actualizarUsuario(usuario);
		log.info("usuario actualizado, el id de este usuario es "+us.getIdUsuario());
		UsuarioResponseDTO usuarioActualizadoDTO = new UsuarioResponseDTO();
		log.info("generando respuesta con token");
		usuarioActualizadoDTO.setId(us.getIdUsuario());
		usuarioActualizadoDTO.setModified(LocalDateTime.now());
		usuarioActualizadoDTO.setToken(jwt.generateToken(us.getEmail()));
		log.info("token generado");
		Token token = new Token();
		token.setToken(usuarioActualizadoDTO.getToken());
		tService.agregarToken(token);
		log.info("token guardado");
		return new ResponseEntity<UsuarioResponseDTO>(usuarioActualizadoDTO, HttpStatus.OK);
	     }else {
		throw new IllegalArgumentException("El usuario no existe, favor crearlo.");
		}
    }
}
