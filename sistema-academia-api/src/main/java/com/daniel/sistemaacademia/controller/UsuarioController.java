package com.daniel.sistemaacademia.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.daniel.sistemaacademia.exception.ErroAutenticacao;
import com.daniel.sistemaacademia.exception.RegraNegocioException;
import com.daniel.sistemaacademia.model.dto.AlunoDTO;
import com.daniel.sistemaacademia.model.dto.InstrutorDTO;
import com.daniel.sistemaacademia.model.entity.Aluno;
import com.daniel.sistemaacademia.model.entity.Instrutor;
import com.daniel.sistemaacademia.model.entity.Usuario;
import com.daniel.sistemaacademia.repository.AlunoRepository;
import com.daniel.sistemaacademia.repository.InstrutorRepository;
import com.daniel.sistemaacademia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.daniel.sistemaacademia.model.dto.UsuarioDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private InstrutorRepository instrutorRepository;

	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Optional<Usuario> usuarioRetorno = usuarioRepository.findByEmail(dto.getEmail());

			// checando se o usuario esta cadastrado no banco de dados
			if (usuarioRetorno.isPresent()) {

				// checando se as senhas são iguais
				if(usuarioRetorno.get().getSenha().equals(dto.getSenha())) {
					return ResponseEntity.ok(usuarioRetorno.get());
				} else {
					return ResponseEntity.badRequest().body("Senha incorreta para o email informado.");
				}

			} else {
				return ResponseEntity.badRequest().body("Usuário com o email informado não cadastrado no banco de dados.");
			}

		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/alunos")
	@Transactional
	public ResponseEntity salvarAluno(@RequestBody AlunoDTO dto) {
		try {
			Usuario usuario = new Usuario().converterPorAlunoDTO(dto);
			usuario = usuarioRepository.save(usuario);
			Aluno entidade = new Aluno().converter(dto);
			entidade.setUsuario(usuario);
			entidade = alunoRepository.save(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/instrutores")
	@Transactional
	public ResponseEntity save(@RequestBody InstrutorDTO dto) {
		try {
			Usuario usuario = new Usuario().converterPorInstrutorDTO(dto);
			usuario = usuarioRepository.save(usuario);
			Instrutor entidade = new Instrutor().converter(dto);
			entidade.setUsuario(usuario);
			entidade = instrutorRepository.save(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{id}/alunos")
	public ResponseEntity obterAlunos(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			List<Aluno> alunos = alunoRepository.findByUsuario(usuario.get());
			return ResponseEntity.ok(alunos.size());
		}

		return ResponseEntity.badRequest().body("Alunos não encontrados para usuário informado.");
	}

}
