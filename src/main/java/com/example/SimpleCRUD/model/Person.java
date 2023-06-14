package com.example.SimpleCRUD.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity //Maps the "Contact" entity to the "Contact" table
@Data
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "person_id")
    private List<Contact> contacts = new ArrayList<>();

    public Person(String name, List<Contact> contacts) {
        this.name = name;
        this.contacts = contacts;
    }
}