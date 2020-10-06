package com.daniel.sistemaacademia.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaDTO {

    private Long aluno;
    private Long desempenho;
    private String avaliador;
    private LocalDate dataAvaliacao;
    private BigDecimal altura;
    private BigDecimal gorduraCorporal;
    private BigDecimal panturrilha;
    private BigDecimal abdomen;
    private BigDecimal torax;
    private BigDecimal quadril;
    private BigDecimal pressao;
    private BigDecimal peso;
    private String frequenciaCardiaca;
    private LocalDate dataDesempenho;

}
