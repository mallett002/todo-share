package com.task.todoshare.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void shouldCreateNewTodo() throws Exception {
        TodoDTO createdDTO = buildNewTodoDTO();

        when(service.createTodo(any(TodoDTO.class)))
                .thenReturn(createdDTO);

        mockMvc.perform(post("/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertToJson(createdDTO))
            .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(convertToJson(createdDTO)))
                .andReturn();
    }

    @Test
    public void shouldGetATodoById() throws Exception {
        TodoDTO todoDTO = buildNewTodoDTO();
        Long id = todoDTO.getId();

        when(service.findById(id))
                .thenReturn(todoDTO);

        mockMvc.perform(get("/todos/" + id))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(convertToJson(todoDTO)));
    }

    @Test
    public void shouldUpdateATodo() throws Exception { // TODO: fix this test
        TodoDTO createdDTO = buildNewTodoDTO();
        TodoDTO updatedDTO = buildNewTodoDTO();

        when(service.updateTodo(eq(1L), any(TodoDTO.class)))
                .thenReturn(updatedDTO);

        mockMvc.perform(put("/todos/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(createdDTO))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(convertToJson(updatedDTO)))
                .andReturn();
    }

    private String convertToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    private TodoDTO buildNewTodoDTO() {
        Long id = 1L;
        String message = generator.createRandomString();

        TodoDTO todoDTO = generator.createNewTodo(message);
        todoDTO.setId(id);

        return todoDTO;
    }
}
