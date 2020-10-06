package com.daniel.sistemaacademia.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrutorDTO {

    private String nome;
    private Long usuario;
    private String senha;
    private String email;
    private String telefone;
    private LocalDate dataAdmissao;
    private Integer tipoUsuario;
}
