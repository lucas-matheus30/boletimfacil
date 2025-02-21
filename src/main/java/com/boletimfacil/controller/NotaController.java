package com.boletimfacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boletimfacil.model.Nota;
import com.boletimfacil.repository.NotaRepository;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotaController {

	@Autowired
	private NotaRepository notaRepository;

	@GetMapping("/{alunoId}")
	public List<Nota> listarNotasPorAluno(@PathVariable String alunoId) {
		return notaRepository.findByAlunoId(alunoId);
	}

	@PostMapping
	public Nota adicionarNota(@RequestBody Nota nota) {
		return notaRepository.save(nota);

	}
}
