package com.boletimfacil.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boletimfacil.model.Professor;

@Repository
public interface ProfessorRepository extends MongoRepository<Professor, String> {

	public List<Professor> findByNomeIgnoreCase(@Param("nome") String nome);

}
