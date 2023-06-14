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
@Table(name="contact")
public class Contact {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "name") // IF the col. name was different from entity vs. database here is how you would set it up
    private String name;
    private String email;
    private String phone;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "person_id")
    private Person person;

    public Contact(String name, String email, String phone, Person person) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.setPerson(person);
    }
}
