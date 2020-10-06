package com.daniel.sistemaacademia.repository;

import com.daniel.sistemaacademia.model.entity.Aluno;
import com.daniel.sistemaacademia.model.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {

    public List<AvaliacaoFisica> findAllByAluno(Aluno aluno);
}
