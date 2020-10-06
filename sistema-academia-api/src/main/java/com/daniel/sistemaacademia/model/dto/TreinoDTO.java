package com.daniel.sistemaacademia.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TreinoDTO {

    private Long aluno;
    private String tipoTreino;
    private String nome;
}
