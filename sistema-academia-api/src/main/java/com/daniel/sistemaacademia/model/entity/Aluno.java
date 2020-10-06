package com.daniel.sistemaacademia.model.entity;

import com.daniel.sistemaacademia.model.dto.AlunoDTO;
import com.daniel.sistemaacademia.repository.UsuarioRepository;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table( name = "aluno" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "data_nascimento")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataNascimento;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "objetivo")
    private String objetivo;

    @Column(name = "matriculado")
    private boolean matriculado;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "profissao")
    private String profissao;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "debito")
    private BigDecimal debito;

    @Column(name = "data_cadastro")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;

    public Aluno converter(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setCpf(dto.getCpf());
        aluno.setRg(dto.getRg());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setEndereco(dto.getEndereco());
        aluno.setBairro(dto.getBairro());
        aluno.setCep(dto.getCep());
        aluno.setCidade(dto.getCidade());
        aluno.setUf(dto.getUf());
        aluno.setObjetivo(dto.getObjetivo());
        aluno.setMatriculado(dto.isMatriculado());
        aluno.setEstadoCivil(dto.getEstadoCivil());
        aluno.setProfissao(dto.getProfissao());
        aluno.setIdade(dto.getIdade());
        aluno.setDebito(dto.getDebito());
        aluno.setDataCadastro(dto.getDataCadastro());
        return aluno;
    }

}
