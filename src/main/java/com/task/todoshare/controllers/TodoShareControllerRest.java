package com.task.todoshare.controllers;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.services.TodoShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoShareControllerRest {

    @Autowired
    TodoShareService todoShareService;

    @PostMapping(value = "/todo/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO postDTO) {
        TodoDTO response = todoShareService.createTodo(postDTO);

        return ResponseEntity.ok(response);
    }
}
