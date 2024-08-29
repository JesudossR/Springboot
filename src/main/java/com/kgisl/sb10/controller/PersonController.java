package com.kgisl.sb10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.sb10.entity.Person;
import com.kgisl.sb10.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    // private final PersonRepository personRepository;

    @Autowired
    // public PersonController(PersonRepository personRepository) {
    //     this.personRepository = personRepository;
    // }
    private PersonService personService;
 
    public PersonController(PersonService personService) {
        this.personService=personService;
    }
 
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        Person person = personService.getPersonById(id);
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        return new ResponseEntity<>(person, person != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") int id) {
        if (personService.getPersonById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // @GetMapping("/persons")
    // public List<Person> getAllPersons() {
    //     // List<Person> persons = personRepository.findAll();
    //     // logger.info("Fetched persons: {}", persons);
    //     return personRepository.findAll();
    // }

    // @GetMapping("/persons/{id}")
    // public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
    //     Optional<Person> person = personRepository.findById(id);
    //     if (person.isPresent()) {
    //         return new ResponseEntity<>(person.get(), HttpStatus.OK); // 200 OK
    //     } else {
    //         return ResponseEntity.notFound().build(); // 404 Not Found
    //     }
    // }

    // @PostMapping("/persons")
    // public ResponseEntity<Person> createPerson(@RequestBody Person person) {
    //     Person createdPerson = personRepository.save(person);
    //     return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    // }

    // @DeleteMapping("/persons/{id}")
    // public ResponseEntity<Void> deletePerson(@PathVariable int id) {
    //     Optional<Person> person = personRepository.findById(id);
    //     if (person.isPresent()) {
    //         personRepository.deleteById(id);
    //         return ResponseEntity.noContent().build(); // 204 No Content (success)
    //     } else {
    //         return ResponseEntity.notFound().build(); // 404 Not Found (failed)
    //     }
    // }
}
