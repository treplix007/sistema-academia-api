package com.daniel.sistemaacademia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.sistemaacademia.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
}
