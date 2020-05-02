package com.task.todoshare.controllers;

import com.task.todoshare.requestBody.TodoRequestBody;
import com.task.todoshare.services.GreetingService;
import com.task.todoshare.services.TodoShareService;
import com.task.todoshare.utils.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoShareControllerRest.class)
@ExtendWith(MockitoExtension.class)
public class TodoShareControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoShareService service;

    TodoRequestBody todoRequestBody;

    @BeforeEach
    private void createTodoRequestBody() {
        todoRequestBody = generator.createNewTodo(generator.createRandomString());
    }

    RandomGenerator generator = new RandomGenerator();

    @Test
    public void shouldCallServiceWithRequestBody() throws Exception {
//        when(service.createTodo(todoRequestBody))
//            .thenReturn(message);
//
//        mockMvc.perform(post("/todo/create", todoRequestBody))
//            .andExpect(status().isOk());
    }
}
