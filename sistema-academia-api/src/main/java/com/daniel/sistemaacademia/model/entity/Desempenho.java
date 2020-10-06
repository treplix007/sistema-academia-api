package com.daniel.sistemaacademia.model.entity;

import com.daniel.sistemaacademia.model.dto.AvaliacaoFisicaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "desempenho" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Desempenho {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "altura")
    private BigDecimal altura;

    @Column(name = "gordura_corporal")
    private BigDecimal gorduraCorporal;

    @Column(name = "panturrilha")
    private BigDecimal panturrilha;

    @Column(name = "abdomen")
    private BigDecimal abdomen;

    @Column(name = "torax")
    private BigDecimal torax;

    @Column(name = "quadril")
    private BigDecimal quadril;

    @Column(name = "pressao")
    private BigDecimal pressao;

    @Column(name = "peso")
    private BigDecimal peso;

    @Column(name = "frequencia_cardiaca")
    private String frequenciaCardiaca;

    @Column(name = "data_desempenho")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataDesempenho;

    public Desempenho converterPorAvaliacaoFisicaoDTO(AvaliacaoFisicaDTO dto) {
        Desempenho desempenho = new Desempenho();
        desempenho.setAbdomen(dto.getAbdomen());
        desempenho.setAltura(dto.getAltura());
        desempenho.setDataDesempenho(dto.getDataDesempenho());
        desempenho.setFrequenciaCardiaca(dto.getFrequenciaCardiaca());
        desempenho.setGorduraCorporal(dto.getGorduraCorporal());
        desempenho.setPanturrilha(dto.getPanturrilha());
        desempenho.setPeso(dto.getPeso());
        desempenho.setPressao(dto.getPressao());
        desempenho.setQuadril(dto.getQuadril());
        desempenho.setTorax(dto.getTorax());
        return desempenho;
    }
}
