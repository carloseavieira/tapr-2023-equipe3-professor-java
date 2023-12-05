package br.edu.univille.microservicoprofessor.controller;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univille.microservicoprofessor.entity.Professor;
import br.edu.univille.microservicoprofessor.service.ProfessorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessorAPIController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores(){
        //var listaProfessores = new ArrayList<Professor>();
        var listaProfessores = service.getAll();
    
        return 
            new ResponseEntity<List<Professor>>(listaProfessores, HttpStatus.OK);        
    }
    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        Professor novoProfessor = service.create(professor);
        return new ResponseEntity<>(novoProfessor, HttpStatus.CREATED);
    }    
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable String id, @RequestBody Professor professor) {
        Professor professorAtualizado = service.update(id, professor);
        return new ResponseEntity<>(professorAtualizado, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> removerCarro(@PathVariable("id")  String id){
        if(id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var professor = service.delete(id);
        if(professor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Professor>
            (professor, HttpStatus.OK);
    }
    
}
