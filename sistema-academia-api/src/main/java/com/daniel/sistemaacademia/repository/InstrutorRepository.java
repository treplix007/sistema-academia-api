package com.daniel.sistemaacademia.repository;

import com.daniel.sistemaacademia.model.entity.Instrutor;
import com.daniel.sistemaacademia.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    public List<Instrutor> findByUsuario(Usuario usuario);

}
