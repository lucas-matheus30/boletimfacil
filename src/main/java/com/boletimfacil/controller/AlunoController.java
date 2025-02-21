package com.boletimfacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.boletimfacil.repository.AlunoRepository;

import jakarta.validation.Valid;

import com.boletimfacil.model.Aluno;


@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> buscarAlunos(){
		return ResponseEntity.ok(alunoRepository.findAll());
	}
	
	@GetMapping("/{matricula}")
	public Optional<Aluno> buscarAlunoPorMatricula(@PathVariable String matricula){
		return alunoRepository.findByMatricula(matricula);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> cadastrarAluno(@Valid @RequestBody Aluno aluno){
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoRepository.save(aluno));
	}
	
	@PutMapping
	public ResponseEntity<Aluno> atualizarAluno(@Valid @RequestBody Aluno alunoAtualizado){
		return alunoRepository.findById(alunoAtualizado.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(alunoRepository.save(alunoAtualizado)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletarAluno(@PathVariable String id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		
		if (aluno.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		alunoRepository.deleteById(id);
	}
}
