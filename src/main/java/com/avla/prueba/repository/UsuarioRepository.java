package com.avla.prueba.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avla.prueba.domain.Usuario;


public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	@Query(value="select * from usuario where email=:correo", nativeQuery=true)
	Optional<Usuario> buscarPorCorreo(@Param("correo")String correo);

}
