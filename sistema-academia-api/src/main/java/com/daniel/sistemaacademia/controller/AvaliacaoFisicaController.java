package com.daniel.sistemaacademia.controller;

import com.daniel.sistemaacademia.exception.RegraNegocioException;
import com.daniel.sistemaacademia.model.dto.AvaliacaoFisicaDTO;
import com.daniel.sistemaacademia.model.entity.Aluno;
import com.daniel.sistemaacademia.model.entity.AvaliacaoFisica;
import com.daniel.sistemaacademia.model.entity.Desempenho;
import com.daniel.sistemaacademia.repository.AlunoRepository;
import com.daniel.sistemaacademia.repository.AvaliacaoFisicaRepository;
import com.daniel.sistemaacademia.repository.DesempenhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacaoFisica")
@RequiredArgsConstructor
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DesempenhoRepository desempenhoRepository;

    @GetMapping
    public ResponseEntity findAll() {
        List<AvaliacaoFisica> avaliacoes = avaliacaoFisicaRepository.findAll();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            return ResponseEntity.ok(avaliacaoFisica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity findByIdAluno(@PathVariable("id") Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        
        if(aluno.isPresent()) {
            List<AvaliacaoFisica> avaliacaoFisicas = avaliacaoFisicaRepository.findAllByAluno(aluno.get());
            return ResponseEntity.ok(avaliacaoFisicas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AvaliacaoFisicaDTO dto) {
        try {
            AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica().converter(dto);

            Optional<Aluno> aluno = alunoRepository.findById(dto.getAluno());
            if(aluno.isPresent()) {
                avaliacaoFisica.setAluno(aluno.get());
            }

            Desempenho desempenho = new Desempenho().converterPorAvaliacaoFisicaoDTO(dto);
            desempenhoRepository.save(desempenho);

            avaliacaoFisica.setDesempenho(desempenho);
            avaliacaoFisica = avaliacaoFisicaRepository.save(avaliacaoFisica);
            return new ResponseEntity(avaliacaoFisica, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id, @RequestBody AvaliacaoFisicaDTO dto) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            AvaliacaoFisica avaliacao = new AvaliacaoFisica().converter(dto);
            avaliacaoFisicaRepository.save(avaliacao);
            return ResponseEntity.ok(avaliacao);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            avaliacaoFisicaRepository.delete(avaliacaoFisica.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/imc/{id}")
    public ResponseEntity calculateIMC(@PathVariable("id") Long id) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            BigDecimal imc = avaliacaoFisica.get().calcularIMC(avaliacaoFisica);
            return ResponseEntity.ok(imc);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/peso-gordo/{id}")
    public ResponseEntity calculatePesoGordo(@PathVariable("id") Long id) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            BigDecimal pesoGordo = avaliacaoFisica.get().calcularPesoGordo(avaliacaoFisica);
            return ResponseEntity.ok(pesoGordo);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/peso-magro/{id}")
    public ResponseEntity calculatePesoMagro(@PathVariable("id") Long id) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            BigDecimal pesoMagro = avaliacaoFisica.get().calcularPesoMagro(avaliacaoFisica);
            return ResponseEntity.ok(pesoMagro);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/peso-residual/{id}")
    public ResponseEntity calculatePesoResidual(@PathVariable("id") Long id) {
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()) {
            BigDecimal pesoResidual = avaliacaoFisica.get().calcularPesoResidual(avaliacaoFisica);
            return ResponseEntity.ok(pesoResidual);
        }

        return ResponseEntity.notFound().build();
    }

}
