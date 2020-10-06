package com.daniel.sistemaacademia.model.entity;

import javax.persistence.*;

import com.daniel.sistemaacademia.model.dto.AlunoDTO;
import com.daniel.sistemaacademia.model.dto.InstrutorDTO;
import com.daniel.sistemaacademia.model.dto.UsuarioDTO;
import com.daniel.sistemaacademia.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "usuario" , schema = "academias")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;

	@Column(name = "tipo_usuario")
	private Integer tipoUsuario;
	
	@Column(name = "senha")
	@JsonIgnore
	private String senha;

	public Usuario converter(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		return usuario;
	}

	public Usuario converterPorAlunoDTO(AlunoDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setEmail(dto.getEmail());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		usuario.setTipoUsuario(TipoUsuario.ALUNO.getValor());
		return usuario;
	}

	public Usuario converterPorInstrutorDTO(InstrutorDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setTipoUsuario(TipoUsuario.INSTRUTOR.getValor());
		return usuario;
	}

}
