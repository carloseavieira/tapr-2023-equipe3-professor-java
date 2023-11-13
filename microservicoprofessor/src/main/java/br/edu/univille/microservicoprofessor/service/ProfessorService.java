package br.edu.univille.microservicoprofessor.service;

import java.util.List;

import br.edu.univille.microservicoprofessor.entity.Professor;

public interface ProfessorService {

    public List<Professor> getAll();
    
}
