package com.daniel.sistemaacademia.model.entity;

import com.daniel.sistemaacademia.model.dto.InstrutorDTO;
import com.daniel.sistemaacademia.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;

@Entity
@Table( name = "instrutor" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrutor {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_admissao")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataAdmissao;

    public Instrutor converter(InstrutorDTO dto) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(dto.getNome());
        instrutor.setTelefone(dto.getTelefone());
        instrutor.setDataAdmissao(dto.getDataAdmissao());
        return instrutor;
    }
}
