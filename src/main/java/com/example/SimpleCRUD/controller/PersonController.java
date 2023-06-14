package com.example.SimpleCRUD.controller;

import com.example.SimpleCRUD.model.Contact;
import com.example.SimpleCRUD.model.Person;
import com.example.SimpleCRUD.repository.ContactRepository;
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
    private PersonRepository personRepository;
    private ContactRepository contactRepository;

    @GetMapping
    public List findAll(){
        return personRepository.findAll();
    }


    @GetMapping(path= {"/{id}"})
    public ResponseEntity<Person> findById(@PathVariable long id){
        return personRepository.findById(id)
                .map(p -> ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Person create(@RequestBody Person newPerson){
        return personRepository.save(newPerson);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Person contact){
        return personRepository.findById(id)
                .map(p -> {
                    p.setName(contact.getName());
                    Person updated = personRepository.save(p);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        return personRepository.findById(id)
                .map(p -> {
                    contactRepository.findAll().stream()
                            .filter(contact -> contact.getPerson().equals(p))
                                    .forEach(contact ->{
                                        contactRepository.delete(contact);
                                    });
                    personRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.badRequest().build());
    }
}
