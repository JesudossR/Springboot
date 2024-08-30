package com.kgisl.sb10.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kgisl.sb10.entity.Person;
import com.kgisl.sb10.service.PersonService;

/**
 * PersonController
 */

@ExtendWith(MockitoExtension.class)

public class PersonControllerTest {
  @Mock
  private PersonService personService;

  @InjectMocks
  private PersonController personController;

  public static List<Person> expected;
  
  Person person1 = new Person(1,"Alice","alice@example.com");
  Person person2 = new Person(2,"Bob","bob@example.com");

  // public Person person2 = new PersonBuilder().id(2L).name("aravinth").build();

  @Test
  public void getallPersonsTest() {

    expected = Arrays.asList(person1, person2);
    System.out.println(expected);
    when(personService.getAllPersons()).thenReturn(expected);
    ResponseEntity<List<Person>> actual = personController.getAllPersons();
    assertNotNull(actual);
    assertEquals(expected, actual.getBody());
    assertEquals(HttpStatus.OK, actual.getStatusCode());
  }

  @Test
  public void getPersonByIdTest() {
    int id = 1;
    when(personService.getPersonById(id)).thenReturn(null);
    ResponseEntity<Person> actual = personController.getPersonById(id);
    assertNotNull(actual);
  }

  @Test
  public void createPersonTest() {
    when(personService.createPerson(person1)).thenReturn(person1);
    personController.createPerson(person1);
  }

  @Test
  public void updatePersonTest() {
    // Person edit = new PersonBuilder().name("shanmugam").build();
    int id = 1;
    // when(personService.updatePerson(id, person1)).thenReturn(person1);
    ResponseEntity<Person> actual = personController.updatePerson(id, person1);
    assertNotNull(actual);
    System.out.println("Actual is  "+actual.getBody());
    System.out.println("expected-->" + expected);
    // assertEquals(edit, actual.getBody());
  }

  @Test
  public void deletePersonTest() {
    int id = 1;
    when(personService.getPersonById(id)).thenReturn(person1);
    personController.deletePerson(id);
    verify(personService).deletePerson(id);
  }
}
