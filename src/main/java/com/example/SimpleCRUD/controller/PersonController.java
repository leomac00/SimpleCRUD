package com.example.SimpleCRUD.controller;

import com.example.SimpleCRUD.model.Person;
import com.example.SimpleCRUD.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/people")
@AllArgsConstructor
public class PersonController {
    private PersonRepository repository;

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }


    @GetMapping(path= {"/{id}"})
    public ResponseEntity<Person> findById(@PathVariable long id){
        return repository.findById(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Person create(@RequestBody Person newPerson){
        return repository.save(newPerson);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Person contact){
        return repository.findById(id)
                .map(p -> {
                    p.setName(contact.getName());
                    Person updated = repository.save(p);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return repository.findById(id)
                .map(p -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.badRequest().build());
    }
}
