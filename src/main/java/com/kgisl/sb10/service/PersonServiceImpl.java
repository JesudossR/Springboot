package com.kgisl.sb10.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.kgisl.sb10.entity.Person;
import com.kgisl.sb10.repository.PersonRepository;
 
@Service
public class PersonServiceImpl implements PersonService {
 
    @Autowired
    private PersonRepository personRepository;
 
    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
 
    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
 
    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse(null);
    }
 
    @Override
    public Person updatePerson(int id, Person updatedPerson) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            // person.setFirstName(updatedPerson.getFirstName());
            // person.setLastName(updatedPerson.getLastName());
            // person.setEmail(updatedPerson.getEmail());
            System.out.println("");
            return personRepository.save(updatedPerson);
        }
        
        return null;
    }
 
    @Override
    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}