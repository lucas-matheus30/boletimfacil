package com.boletimfacil.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.boletimfacil.model.Aluno;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String>{
	
	Optional<Aluno> findByMatricula(String matricula);

}
