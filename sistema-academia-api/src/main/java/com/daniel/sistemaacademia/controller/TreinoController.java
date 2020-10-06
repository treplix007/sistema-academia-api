package com.daniel.sistemaacademia.controller;

import com.daniel.sistemaacademia.model.dto.TreinoDTO;
import com.daniel.sistemaacademia.model.entity.Aluno;
import com.daniel.sistemaacademia.model.entity.AvaliacaoFisica;
import com.daniel.sistemaacademia.model.entity.Treino;
import com.daniel.sistemaacademia.model.entity.Usuario;
import com.daniel.sistemaacademia.repository.AlunoRepository;
import com.daniel.sistemaacademia.repository.TreinoRepository;
import com.daniel.sistemaacademia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treinos")
@RequiredArgsConstructor
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity findAll() {
        try {
            List<Treino> treinos = treinoRepository.findAll();
            return ResponseEntity.ok(treinos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            Optional<Treino> treino = treinoRepository.findById(id);
            if(treino.isPresent()) {
                return ResponseEntity.ok(treino);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity findByIdAluno(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            List<Aluno> aluno = alunoRepository.findByUsuario(usuario.get());
            if(!aluno.isEmpty()) {
                return ResponseEntity.ok(treinoRepository.findAllByAluno(aluno.get(0)));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody TreinoDTO treinoDTO) {
        try {
            Optional<Aluno> aluno = alunoRepository.findById(treinoDTO.getAluno());
            if (aluno.isPresent()) {
                Treino treino = new Treino().converter(treinoDTO, aluno.get());
                treino = treinoRepository.save(treino);
                return ResponseEntity.ok(treino);
            } else {
                return ResponseEntity.badRequest().body("Aluno não encontrado!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Treino> treino = treinoRepository.findById(id);
        if(treino.isPresent()) {
            treinoRepository.delete(treino.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Treino não encontrado.");
        }
    }

}
