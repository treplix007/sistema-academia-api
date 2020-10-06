package com.daniel.sistemaacademia.model.entity;

import com.daniel.sistemaacademia.model.dto.ExercicioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "exercicio" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercicio {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_treino")
    private Treino treino;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dica")
    private String dica;

    @Column(name = "grupo_muscular")
    private String grupoMuscular;

    @Column(name = "repeticoes")
    private Integer repeticoes;

    @Column(name = "carga")
    private Integer carga;

    @Column(name = "series")
    private Integer series;

    public Exercicio converter(ExercicioDTO dto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(dto.getNome());
        exercicio.setDica(dto.getDica());
        exercicio.setCarga(dto.getCarga());
        exercicio.setRepeticoes(dto.getRepeticoes());
        exercicio.setSeries(dto.getSeries());
        exercicio.setGrupoMuscular(dto.getGrupoMuscular());
        return exercicio;
    }

}
