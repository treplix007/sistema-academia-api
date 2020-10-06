package com.daniel.sistemaacademia.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.time.LocalDate;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
@Table( name = "frequencia" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Frequencia {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column(name = "data_entrada")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataEntrada;

    @Column(name = "data_saida")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataSaida;

}
