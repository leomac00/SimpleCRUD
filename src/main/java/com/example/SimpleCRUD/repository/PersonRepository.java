package com.example.SimpleCRUD.repository;

import com.example.SimpleCRUD.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
