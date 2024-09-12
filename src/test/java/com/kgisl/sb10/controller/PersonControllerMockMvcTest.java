package com.kgisl.sb10.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetallPersonsEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/get"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonByIdEndpoint() throws Exception {
        int personId = 1; // Adjust this ID based on your test data
        mockMvc.perform(MockMvcRequestBuilders.get("/person/get/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void testCreatePersonEndpoint() throws Exception {
        String newPersonJson = "{\"uname\":\"John Doe\",\"email\":\"email@email.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/person/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPersonJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdatePersonEndpoint() throws Exception {
        int personId = 1; // Adjust this ID based on your test data
        String updatedPersonJson = "{\"uname\":\"John Doe update\",\"email\":\"updateemail@email.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/person/put/{id}", personId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPersonJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeletePersonEndpoint() throws Exception {
        int personId = 1; // Adjust this ID based on your test data
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/{id}", personId))
                .andExpect(status().isNoContent());
    }

}