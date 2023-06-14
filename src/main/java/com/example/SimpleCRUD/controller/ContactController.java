package com.example.SimpleCRUD.controller;

import com.example.SimpleCRUD.model.Contact;
import com.example.SimpleCRUD.repository.ContactRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    private ContactRepository repository;

    @GetMapping
    public List findAll(){
        return repository.getAll(); // How do I make it return all entries with the foreign key(person_id)?
    }


    @GetMapping(path= {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return repository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Contact create(@RequestBody Contact newContact){

        return repository.save(newContact);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Contact contact){
        return repository.findById(id)
                .map(c -> {
                    c.setName(contact.getName());
                    c.setEmail(contact.getEmail());
                    c.setPhone(contact.getPhone());
                    Contact updated = repository.save(c);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return repository.findById(id)
                .map(c -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.badRequest().build());
    }
}
