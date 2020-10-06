package com.daniel.sistemaacademia.controller;

import com.daniel.sistemaacademia.exception.RegraNegocioException;
import com.daniel.sistemaacademia.model.dto.AlunoDTO;
import com.daniel.sistemaacademia.model.dto.InstrutorDTO;
import com.daniel.sistemaacademia.model.entity.Aluno;
import com.daniel.sistemaacademia.model.entity.Instrutor;
import com.daniel.sistemaacademia.model.entity.Usuario;
import com.daniel.sistemaacademia.model.enums.TipoUsuario;
import com.daniel.sistemaacademia.repository.InstrutorRepository;
import com.daniel.sistemaacademia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instrutores")
@RequiredArgsConstructor
public class InstrutorController {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity findAll() {
        List<Instrutor> instrutores = instrutorRepository.findAll();
        List<Instrutor> lstIntrutores = new ArrayList<>();
        for(Instrutor instrutor : instrutores) {
            if(instrutor.getUsuario().getTipoUsuario().equals(TipoUsuario.INSTRUTOR.getValor())) {
                lstIntrutores.add(instrutor);
            }
        }
        return ResponseEntity.ok(lstIntrutores);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);

        // checando se o instrutor com o id está na base de dados
        if(instrutor.isPresent() && instrutor.get().getUsuario().getTipoUsuario().equals(TipoUsuario.INSTRUTOR.getValor())) {
            return ResponseEntity.ok(instrutor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/instrutores")
    public ResponseEntity findByIdUsuario(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            List<Instrutor> instrutores = instrutorRepository.findByUsuario(usuario.get());
            return ResponseEntity.ok(instrutores);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody InstrutorDTO dto) {
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);
        if(instrutor.isPresent()) {
            Instrutor novoInstrutor = new Instrutor().converter(dto);
            instrutorRepository.save(novoInstrutor);
            return ResponseEntity.ok(novoInstrutor);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Instrutor> instrutor = instrutorRepository.findById(id);
        if(instrutor.isPresent()) {
            instrutorRepository.delete(instrutor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Instrutor não encontrado.");
        }
    }
}
