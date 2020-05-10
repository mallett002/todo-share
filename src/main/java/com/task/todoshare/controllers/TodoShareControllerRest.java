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
    final String postEndpoint = "/todos";
    final String byIdEndpoint = "/todos/{id}";

    @Autowired
    TodoShareService todoShareService;

    @PostMapping(value = postEndpoint, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO postDTO, UriComponentsBuilder builder) {
        TodoDTO response = todoShareService.createTodo(postDTO);

        UriComponents uriComponents = builder.path(postEndpoint).build();

        return ResponseEntity.created(uriComponents.toUri()).body(response);
    }

    @GetMapping(value = byIdEndpoint)
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO response = todoShareService.findById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = byIdEndpoint, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDTO> updateTodo(
        @RequestBody TodoDTO todoDTO,
        @PathVariable Long id) {
        TodoDTO response = todoShareService.updateTodo(id, todoDTO);

        return ResponseEntity.ok(response);
    }

}
