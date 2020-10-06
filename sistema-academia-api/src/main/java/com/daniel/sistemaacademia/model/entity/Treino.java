package com.daniel.sistemaacademia.model.entity;

import com.daniel.sistemaacademia.model.dto.TreinoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "treino" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column(name = "tipo_treino")
    private String tipoTreino;

    @Column(name = "nome")
    private String nome;

    public Treino converter(TreinoDTO treinoDTO, Aluno aluno) {
        Treino treino = new Treino();
        treino.setAluno(aluno);
        treino.setTipoTreino(treinoDTO.getTipoTreino());
        treino.setNome(treinoDTO.getNome());
        return treino;
    }

}
