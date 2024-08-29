package com.kgisl.sb10.service;
import java.util.List;
 
import com.kgisl.sb10.entity.Person;
 
public interface PersonService {
 
    List<Person> getAllPersons();
 
    Person createPerson(Person person);
 
    Person getPersonById(int id);
 
    Person updatePerson(int id, Person updatedPerson);
 
    void deletePerson(int id);
}