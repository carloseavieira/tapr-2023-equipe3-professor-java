package br.edu.univille.microservicoprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservicoprofessor.entity.Professor;
import br.edu.univille.microservicoprofessor.repositorory.ProfessorRepository;
import br.edu.univille.microservicoprofessor.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Override
    public List<Professor> getAll() {

        var iterador = repository.findAll();
        List<Professor> listaProfessores = new ArrayList<>();

        iterador.forEach(listaProfessores::add);

        /*while(iterador.iterator().hasNext()){
            var umItem = iterador.iterator().next();
            listaProfessores.add(umItem);
        }*/

        return listaProfessores;        
    }    
}
