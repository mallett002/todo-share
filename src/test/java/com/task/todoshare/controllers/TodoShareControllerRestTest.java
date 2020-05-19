package com.task.todoshare.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.services.TodoShareService;
import com.task.todoshare.utils.RandomGenerator;
import com.task.todoshare.utils.TodoNotFoundException;
import org.junit.jupiter.api.Disabled;
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

//TODO: Consider defining a bean of type 'com.task.todoshare.services.JwtUserDetailsService' in your TEST configuration.

@WebMvcTest(TodoShareControllerRest.class)
@ExtendWith(MockitoExtension.class)
public class TodoShareControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoShareService service;

    RandomGenerator generator = new RandomGenerator();
    ObjectMapper mapper = new ObjectMapper();

    @Disabled
    @Test
    public void shouldCreateNewTodo() throws Exception {
        TodoDTO createdDTO = generator.buildNewTodoDTO();

        when(service.createTodo(any(TodoDTO.class)))
                .thenReturn(createdDTO);

        mockMvc.perform(post("/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertToJson(createdDTO))
            .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(convertToJson(createdDTO)));
    }

    @Disabled
    @Test
    public void shouldGetATodoById() throws Exception {
        TodoDTO todoDTO = generator.buildNewTodoDTO();
        Long id = todoDTO.getId();

        when(service.findById(id))
                .thenReturn(todoDTO);

        mockMvc.perform(get("/todos/" + id))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(convertToJson(todoDTO)));
    }

    @Disabled
    @Test
    public void shouldReturnNotFoundWhenFindByIdCantFindById() throws Exception {
        TodoDTO todoDTO = generator.buildNewTodoDTO();
        Long id = todoDTO.getId();

        when(service.findById(id))
                .thenThrow(new TodoNotFoundException(id));

        mockMvc.perform(get("/todos/" + id))
            .andExpect(status().isNotFound())
            .andExpect(MockMvcResultMatchers
                .content().string("Could not find todo item with id " + id));
    }

    @Disabled
    @Test
    public void shouldUpdateATodo() throws Exception {
        TodoDTO createdDTO = generator.buildNewTodoDTO();
        TodoDTO updatedDTO = generator.buildNewTodoDTO();

        when(service.updateTodo(eq(1L), any(TodoDTO.class)))
                .thenReturn(updatedDTO);

        mockMvc.perform(put("/todos/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(createdDTO))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(convertToJson(updatedDTO)));
    }

    @Disabled
    @Test
    public void shouldReturnNotFoundWhenUpdateTodoCantFindById() throws Exception {
        TodoDTO createdDTO = generator.buildNewTodoDTO();
        Long id = createdDTO.getId();

        when(service.updateTodo(eq(id), any(TodoDTO.class)))
                .thenThrow(new TodoNotFoundException(id));

        mockMvc.perform(put("/todos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(createdDTO))
                .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers
                        .content().string("Could not find todo item with id " + id));
    }

    @Disabled
    @Test
    public void shouldDeleteATodo() throws Exception {
        Long id = 1L;

        when(service.deleteTodo(id))
                .thenReturn(id);

        mockMvc.perform(delete("/todos/" + id))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(convertToJson(id)));
    }

    @Disabled
    @Test
    public void shouldNotFoundWhenDeleteTodoNotFindingById() throws Exception {
        Long id = 1L;

        when(service.deleteTodo(id))
                .thenThrow(new TodoNotFoundException(id));

        mockMvc.perform(delete("/todos/" + id))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers
                        .content().string("Could not find todo item with id " + id));
    }

    private String convertToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
