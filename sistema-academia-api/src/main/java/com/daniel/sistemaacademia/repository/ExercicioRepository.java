package com.daniel.sistemaacademia.repository;

import com.daniel.sistemaacademia.model.entity.Exercicio;
import com.daniel.sistemaacademia.model.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    public List<Exercicio> findAllByTreino(Treino treino);
}
