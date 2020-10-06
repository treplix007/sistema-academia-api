package com.daniel.sistemaacademia.model.entity;

import com.daniel.sistemaacademia.model.dto.AvaliacaoFisicaDTO;
import com.daniel.sistemaacademia.repository.AlunoRepository;
import com.daniel.sistemaacademia.repository.DesempenhoRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table( name = "avaliacao_fisica" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisica {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_desempenho")
    private Desempenho desempenho;

    @Column(name = "avaliador")
    private String avaliador;

    @Column(name = "data_avaliacao")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataAvaliacao;

    public AvaliacaoFisica converter(AvaliacaoFisicaDTO dto) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        avaliacaoFisica.setAvaliador(dto.getAvaliador());
        avaliacaoFisica.setDataAvaliacao(dto.getDataAvaliacao());

        return avaliacaoFisica;
    }

    public BigDecimal calcularIMC(Optional<AvaliacaoFisica> avaliacaoFisica) {
        // calcular IMC = peso / (altura * altura)
        BigDecimal peso = avaliacaoFisica.get().getDesempenho().getPeso();
        BigDecimal altura = avaliacaoFisica.get().getDesempenho().getAltura();
        return peso.divide((altura.multiply(altura)));  // calculo
    }

    public BigDecimal calcularPesoGordo(Optional<AvaliacaoFisica> avaliacaoFisica) {
        // calcular Peso Gordo = peso corporal * % gordura
        BigDecimal peso = avaliacaoFisica.get().getDesempenho().getPeso();
        BigDecimal cemPorcento = new BigDecimal(100);
        BigDecimal gordura = avaliacaoFisica.get().getDesempenho().getGorduraCorporal();
        return peso.multiply(gordura).divide(cemPorcento);  // calculo
    }

    public BigDecimal calcularPesoMagro(Optional<AvaliacaoFisica> avaliacaoFisica) {
        // calcular Peso Magro = peso corporal - peso gordo
        BigDecimal peso = avaliacaoFisica.get().getDesempenho().getPeso();
        BigDecimal pesoGordo = calcularPesoGordo(avaliacaoFisica);
        BigDecimal pesoMagro = ( peso.subtract(pesoGordo) );  // calculo
        return peso.divide(pesoMagro);
    }

    public BigDecimal calcularPesoResidual(Optional<AvaliacaoFisica> avaliacaoFisica) {
        // calcular Peso Residual = peso corporal * 0,24
        BigDecimal peso = avaliacaoFisica.get().getDesempenho().getPeso();
        return peso.multiply(BigDecimal.valueOf(0.24));
    }
}
