package br.edu.univille.microservicoprofessor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.univille.microservicoprofessor.entity.Professor;
import br.edu.univille.microservicoprofessor.repository.ProfessorRepository;
import br.edu.univille.microservicoprofessor.service.ProfessorService;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    private DaprClient client = new DaprClientBuilder().build();
    @Value("${app.component.topic.professor}")
    private String TOPIC_NAME;
    @Value("${app.component.service}")
	private String PUBSUB_NAME;

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

    @Override
    public Professor create(Professor professor) {
        publicarAtualizacao(professor);
        return repository.save(professor);        
    }

    @Override
    public Professor update(String id, Professor professor) {
        professor.setId(id); // Garantindo que o ID é o mesmo
        publicarAtualizacao(professor);
        return repository.save(professor);
    }

    @Override
    public Professor delete(String id) {
        var buscaProfessor = repository.findById(id);
        if (buscaProfessor.isPresent()){
            var professor = buscaProfessor.get();

            repository.delete(professor);

            return professor;
        }
        return null;
    }

    private void publicarAtualizacao(Professor professor){
        client.publishEvent(
					PUBSUB_NAME,
					TOPIC_NAME,
					professor).block();
    }
}