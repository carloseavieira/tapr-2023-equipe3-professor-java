package br.edu.univille.microservicoprofessor.service;

import java.util.List;

import br.edu.univille.microservicoprofessor.entity.Aluno;

public interface AlunoService {
    List<Aluno> getAll();
    Aluno create(Aluno aluno);
    Aluno update(String id, Aluno aluno);
    Aluno update(Aluno aluno);
    /*void delete(String id);*/
    public Aluno delete(String id);
}