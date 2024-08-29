package com.kgisl.sb10.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.kgisl.sb10.entity.Person;

public interface PersonRepository extends ListCrudRepository<Person, Integer> {
    
}
