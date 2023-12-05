package br.edu.univille.microservicoprofessor.repository;

import br.edu.univille.microservicoprofessor.entity.Aluno;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository
    extends CrudRepository<Aluno,String>{
}