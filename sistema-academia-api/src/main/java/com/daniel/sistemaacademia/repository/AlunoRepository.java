package com.daniel.sistemaacademia.repository;

import com.daniel.sistemaacademia.model.entity.Aluno;
import com.daniel.sistemaacademia.model.entity.Usuario;
import com.daniel.sistemaacademia.model.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    public List<Aluno> findByUsuario(Usuario usuario);


    @Query("SELECT a FROM Aluno a " +
           "INNER JOIN Usuario u ON a.usuario = u.id " +
           "WHERE u.tipoUsuario = ?1")
    public List<Aluno> findByUsuarioWhereUsuarioTipoUsuario(Integer tipoUsuario);

    @Query("SELECT a FROM Aluno a " +
            "INNER JOIN Usuario u ON a.usuario = u.id " +
            "WHERE u.tipoUsuario = ?1 AND " +
            "a.id = ?2")
    public Optional<Aluno> findByIdAlunoAndUsuarioWhereUsuarioTipoUsuario(Integer tipoUsuario, Long id);
}
