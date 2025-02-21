package com.boletimfacil.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.boletimfacil.model.Nota;

@Repository
public interface NotaRepository extends MongoRepository<Nota, String> {

	List<Nota> findByAlunoId(String alunoId);

}
