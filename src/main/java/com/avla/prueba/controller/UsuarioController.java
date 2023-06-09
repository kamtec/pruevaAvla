package com.avla.prueba.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.avla.prueba.domain.Telefono;
import com.avla.prueba.domain.Token;
import com.avla.prueba.domain.Usuario;
import com.avla.prueba.dto.UsuarioResponseDTO;
import com.avla.prueba.expeption.BadRequestException;
import com.avla.prueba.expeption.NotFoundException;
import com.avla.prueba.service.TelefonoService;
import com.avla.prueba.service.TokenService;
import com.avla.prueba.service.UsuarioService;
import com.avla.prueba.utils.JwtUtils;
import com.avla.prueba.utils.ValidarUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuario")
@Api(tags = {"usuarios"})
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private TelefonoService telService;
	
	@Autowired
	private TokenService tService;
	
	@Autowired
	private JwtUtils jwt;
	
	@Autowired
	private ValidarUtils validar;
	
	@PostMapping("/agregar")
	@ApiOperation(value = "Agregar un usuario", notes = "Agrega un usuario y genera token a partir de el", tags = {"usuarios"})
	public ResponseEntity<UsuarioResponseDTO> agregarUsuario(@ApiParam(value = "informacion del usuario, esta es email, contraseņa, nombre y telefonos", required = true)  @Valid @RequestBody Usuario usuario){
		if(!validar.ValidateEmail(usuario.getEmail())) {
			 throw new BadRequestException("El fromato del email no es valido");
		}
		if(!validar.validate(usuario.getPassword())) {
			throw new BadRequestException("El fromato de la contraseņa no es valido");
		}else {
		Optional<Usuario> usuarioEncontrado = service.encontrarUsuarioPorCorreo(usuario.getEmail());
		if(!usuarioEncontrado.isPresent()) {
		log.info("creando usuario");
		Usuario us = service.agregarUsuario(usuario);
		log.info("usuario creado con el id "+us.getIdUsuario());
		List<Telefono> telefonos = new ArrayList<Telefono>();
		telefonos.addAll(usuario.phones);
		for(int i = 0; i < telefonos.size(); i++) {
		telefonos.get(i).setUsuario(usuario);
	    Telefono telefonoGuardado = telService.agregarTelefono(telefonos.get(i));
		}
		UsuarioResponseDTO usuarioCreadoDTO = new UsuarioResponseDTO();
		log.info("generando respuesta con token");
		usuarioCreadoDTO.setId(us.getIdUsuario());
		usuarioCreadoDTO.setCreated(new Date());
		usuarioCreadoDTO.setToken(jwt.generateToken(us.getEmail()));
		log.info("token generado");
		Token token = new Token();
		token.setToken(usuarioCreadoDTO.getToken());
		tService.agregarToken(token);
		log.info("token guardado");
		return new ResponseEntity<UsuarioResponseDTO>(usuarioCreadoDTO, HttpStatus.OK);
		}else {
		throw new BadRequestException("El correo ya se encuentra registrado.");
		}
		}
	}
	
	@PutMapping("/actualizar")
	@ApiOperation(value = "Actualiza un usuario", notes = "Actualiza un usuario y genera token a partir de el", tags = {"usuarios"})
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@ApiParam(value = "informacion del usuario, esta es email, contraseņa, nombre y telefonos", required = true)  @Valid @RequestBody Usuario usuario){
	Optional<Usuario> usuarioEncontradoPorId = service.encontrarUsuarioPorIdAactualizar(usuario.getIdUsuario());
	  if(usuarioEncontradoPorId.isPresent()) {
		  if(!validar.ValidateEmail(usuario.getEmail())) {
			 throw new BadRequestException("El fromato del email no es valido");
			}
			if(!validar.validate(usuario.getPassword())) {
				throw new BadRequestException("El fromato de la contraseņa no es valido");
			}else {
	    log.info("actualizando usuario");
		Usuario us =service.actualizarUsuario(usuario);
		log.info("usuario actualizado, el id de este usuario es "+us.getIdUsuario());
		List<Telefono> telefonoActs = new ArrayList<Telefono>();
		telefonoActs.addAll(usuario.phones);
		for(int i = 0; i < telefonoActs.size(); i++) {
		Optional<Telefono> telefonoEncontradoPorId = telService.encontrarTelefonoPorIdParaActualizar(telefonoActs.get(i).getIdTelefono());
		if(telefonoEncontradoPorId.isPresent()) {
	    telefonoActs.get(i).setIdTelefono(telefonoEncontradoPorId.get().getIdTelefono());
		telefonoActs.get(i).setUsuario(usuario);
	    Telefono telefonoActualizado = telService.actualizarTelefono(telefonoActs.get(i));
		} else {
	    	throw new BadRequestException("El telefono del usuario no existe, favor crearlo.");
	    }
		}
		UsuarioResponseDTO usuarioActualizadoDTO = new UsuarioResponseDTO();
		log.info("generando respuesta con token");
		usuarioActualizadoDTO.setId(us.getIdUsuario());
		usuarioActualizadoDTO.setModified(new Date());
		usuarioActualizadoDTO.setToken(jwt.generateToken(us.getEmail()));
		log.info("token generado");
		Token token = new Token();
		token.setToken(usuarioActualizadoDTO.getToken());
		tService.agregarToken(token);
		log.info("token guardado");
		return new ResponseEntity<UsuarioResponseDTO>(usuarioActualizadoDTO, HttpStatus.OK);	
		}
		}else {
		throw new BadRequestException("El usuario no existe, favor crearlo.");
		}
	}
	
	@GetMapping("/listar-usuarios")
	@ApiOperation(value = "Obtiene los usuarios", notes = "Obtiene los usuarios con sus telefonos asociados", tags = {"usuarios"})
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> lista = service.consultarUsuarios();
		if(lista.size() != 0) {
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
		}else {
		throw new NotFoundException("No existen usuarios registrados.");	
		}
	}
	
	@GetMapping("/encontrar-usuario/{id}")
	@ApiOperation(value = "Obtiene un usuario por su id", notes = "Obtiene un usuario por su id con sus telefonos asociados", tags = {"usuarios"})
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Long id){
		Usuario usuario = service.encontrarUsuarioPorId(id);
		if(usuario.getIdUsuario() == null) {
	 throw new NotFoundException("No existe usuario registrado con el id "+id+".");		
		}
		else {
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); 
		}	
	}
}
