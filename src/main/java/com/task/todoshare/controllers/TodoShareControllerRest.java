package com.task.todoshare.controllers;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.services.TodoShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TodoShareControllerRest {

    @Autowired
    TodoShareService todoShareService;

    @PostMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO postDTO, UriComponentsBuilder builder) {
        TodoDTO response = todoShareService.createTodo(postDTO);

        UriComponents uriComponents = builder.path("/todos").build();

        return ResponseEntity.created(uriComponents.toUri()).body(response);
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO response = todoShareService.findById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> updateTodo(
        @RequestBody TodoDTO todoDTO,
        @PathVariable Long id) {
        TodoDTO response = todoShareService.updateTodo(id, todoDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<Long> deleteTodo(@PathVariable Long id) {
        Long deletedId = todoShareService.deleteTodo(id);

        return ResponseEntity.ok(deletedId);
    }

}
