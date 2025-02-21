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

import com.boletimfacil.repository.ProfessorRepository;

import jakarta.validation.Valid;

import com.boletimfacil.model.Professor;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping
	public ResponseEntity<List<Professor>> buscarProfessores(){
		return ResponseEntity.ok(professorRepository.findAll());
	}
	
	@GetMapping("/{nome}")
	public List<Professor> buscarProfessorPorNome(@PathVariable String nome){
		return professorRepository.findByNomeIgnoreCase(nome);
	}
	
	@PostMapping
	public ResponseEntity<Professor> cadastrarProfessor(@Valid @RequestBody Professor professor){
		return ResponseEntity.status(HttpStatus.CREATED).body(professorRepository.save(professor));
	}
	
	@PutMapping
	public ResponseEntity<Professor> atualizarProfessor(@Valid @RequestBody Professor professorAtualizado){
		return professorRepository.findById(professorAtualizado.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(professorRepository.save(professorAtualizado)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletarProfessor(@PathVariable String id) {
		Optional<Professor> professor = professorRepository.findById(id);
		
		if (professor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		professorRepository.deleteById(id);
	}
}
