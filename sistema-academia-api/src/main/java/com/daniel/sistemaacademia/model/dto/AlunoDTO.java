package com.daniel.sistemaacademia.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private String nome;
    private Long usuario;
    private String senha;
    private String email;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    private String endereco;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String objetivo;
    private boolean matriculado;
    private String estadoCivil;
    private String profissao;
    private Integer idade;
    private BigDecimal debito;
    private LocalDate dataCadastro;
    private Integer tipoUsuario;
}
