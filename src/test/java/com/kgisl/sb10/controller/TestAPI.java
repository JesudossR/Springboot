package com.kgisl.sb10.controller;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kgisl.sb10.entity.Person;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAPI {
 
  private Playwright playwright;
  private APIRequestContext request;
  // private static final String BaseURL = "http://localhost:10000/person";
 
  @BeforeAll
  void beforeAll() {
    createPlaywright();
    createAPIRequestContext();
  }
 
  void createPlaywright() {
    playwright = Playwright.create();
  }
 
  void createAPIRequestContext() {
    Map<String, String> headers = new HashMap<>();
    // We set this header per your developer guidelines.
    // headers.put("Accept", "application/json");
    // Add authorization token to all requests.
    // Assuming personal access token available in the environment.
    // headers.put("Authorization", "token " + API_TOKEN);
 
    request = playwright.request().newContext(new APIRequest.NewContextOptions()
        // All requests we send go to this API endpoint.
        .setBaseURL("http://localhost:8081")
        .setExtraHTTPHeaders(headers));
  }
 
  void disposeAPIRequestContext() {
    if (request != null) {
      request.dispose();
      request = null;
    }
  }
 
  void closePlaywright() {
    if (playwright != null) {
      playwright.close();
      playwright = null;
    }
  }
 
  @AfterAll
  void afterAll() {
    disposeAPIRequestContext();
    closePlaywright();
  }
 
  @Test
  void shouldCreatePerson() {
    Map<String, String> data = new HashMap<>();
    data.put("uname", "Jesu");
    data.put("email", "jesu@email.com");
 
    APIResponse response = request.post("/person/post", RequestOptions.create().setData(data));
 
    Person person = new Gson().fromJson(response.text(), Person.class);
 
    assertEquals(201, response.status());
    // assertEquals("OK", response.statusText());
   
    assertEquals(person.uname(), "Jesu");
    assertEquals(person.email(), "jesu@email.com");
  }
 
  @Test
  void shouldGetAllPersons() {
    // SELECT count(*) FROM PERSON
    // http://localhost:10000/person
    // https://jsonpathfinder.com/
 
    APIResponse response = request.get("/person/get");
 
    JsonArray json = new Gson().fromJson(response.text(), JsonArray.class);
    assertEquals(json.size(), json.size());
    // JsonElement firstValue = json.get(0);
    Person firstPerson = new Gson().fromJson(json.get(0), Person.class);
 
    assertEquals(firstPerson.uname(), "Alice");
    assertEquals(firstPerson.email(), "alice@example.com");
  }
 
  @Test
  void shouldGetPerson() {
    // http://localhost:10000/person/5
    // {"id":5,"firstName":"Emily","lastName":"Davis","email":"emily.davis@example.com"}
 
    APIResponse response = request.get("/person/get/2");
 
    Person person = new Gson().fromJson(response.text(), Person.class);
 
    assertEquals(200, response.status());
    // assertEquals("OK", response.statusText());
    assertEquals(person.uname(), "Bob");    
    assertEquals(person.email(), "bob@example.com");
  }
 
  @Test
  void shouldDeletePerson() {
    // http://localhost:10000/person/6
    APIResponse response = request.delete("/person/delete/71");
    assertEquals(204, response.status());
  }
 
  @Test
  void shouldUpdatePerson() {
    // {"id":87,"firstName":"Jane","lastName":"Smith","email":"jane.smith@example.com"},{"id":88,"firstName":"Alice","lastName":"Jones","email":"alice.jones@example.com"}
    Map<String, String> data = new HashMap<>();
    data.put("id", "5");
    data.put("uname", "Jesudoss");
    data.put("email", "jesudoss@gmail.com");
 
    APIResponse response = request.put("/person/put/5", RequestOptions.create().setData(data));
    Person person = new Gson().fromJson(response.text(), Person.class);
    // {"id":87,"firstName":"PlaywrightF87","lastName":"PlaywrightL87","email":"email87@email.com"}
    assertEquals(200, response.status());
    // assertEquals("OK", response.statusText());
    assertEquals(person.uname(), "Jesudoss");
    assertEquals(person.email(), "jesudoss@gmail.com");
  }
}