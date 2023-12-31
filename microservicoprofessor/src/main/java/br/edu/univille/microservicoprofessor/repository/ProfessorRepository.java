package br.edu.univille.microservicoprofessor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.univille.microservicoprofessor.entity.Professor;

@Repository
public interface ProfessorRepository 
    extends CrudRepository<Professor,String>{
    
}
