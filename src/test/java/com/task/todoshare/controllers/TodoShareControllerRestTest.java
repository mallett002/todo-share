package com.task.todoshare.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.services.TodoShareService;
import com.task.todoshare.utils.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoShareControllerRest.class)
@ExtendWith(MockitoExtension.class)
public class TodoShareControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoShareService service;

    RandomGenerator generator = new RandomGenerator();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCallServiceWithRequestBody() throws Exception {
        String message = generator.createRandomString();
        TodoDTO createdDTO = generator.createNewTodo(message);
        createdDTO.setId(1L);

        when(service.createTodo(any(TodoDTO.class)))
                .thenReturn(createdDTO);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(createdDTO);

        mockMvc.perform(post("/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(json))
                .andReturn();
    }
}
