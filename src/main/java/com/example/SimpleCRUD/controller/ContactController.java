package com.example.SimpleCRUD.controller;

import com.example.SimpleCRUD.model.Contact;
import com.example.SimpleCRUD.model.ContactDTO;
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
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    private ContactRepository contactRepository;
    private PersonRepository personRepository;

    @GetMapping
    public List findAll(){
        return contactRepository.findAll();
    }


    @GetMapping(path= {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return contactRepository.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity create(@RequestBody ContactDTO contactDTO){

        return personRepository.findById(contactDTO.getPerson_id())
                .map(p -> {
                    Contact newContact = new Contact(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone(), p);
                    contactRepository.save(newContact);
                    return ResponseEntity.ok().body(newContact);
                }).orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Contact contact){

        return contactRepository.findById(id)
                .map(c -> {
                    c.setName(contact.getName());
                    c.setEmail(contact.getEmail());
                    c.setPhone(contact.getPhone());
                    Contact updated = contactRepository.save(c);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return contactRepository.findById(id)
                .map(c -> {
                    c.setPerson(null);
                    contactRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.badRequest().build());
    }
}
