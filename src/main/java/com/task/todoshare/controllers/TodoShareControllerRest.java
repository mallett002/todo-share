package com.task.todoshare.controllers;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.services.TodoShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoShareControllerRest {

    @Autowired
    TodoShareService todoShareService;

    @PostMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO postDTO) {
        TodoDTO response = todoShareService.createTodo(postDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO response = todoShareService.findById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }

}
