package com.daniel.sistemaacademia.model.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioDTO {

    private String nome;
    private Long treino;
    private String dica;
    private String grupoMuscular;
    private Integer repeticoes;
    private Integer carga;
    private Integer series;
}
