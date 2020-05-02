package com.task.todoshare.services;

import com.task.todoshare.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import com.task.todoshare.repository.TodoShareRepository;
import com.task.todoshare.requestBody.TodoRequestBody;
import com.task.todoshare.responseBody.TodoCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// hibernate client (uses entityManager)
@Service
public class TodoShareService {

    @Autowired
    TodoShareRepository todoShareRepository;

    private TodoDTO mapToDTO(TodoEntity todoEntity) {
//        TodoCreationResponse response = new TodoCreationResponse();
//
//        response.setMessage(todo.getMessage());
//        response.setId(todo.getId());
//        response.
//
//        return response;
        return new TodoDTO();
    }

    public TodoDTO createTodo(TodoDTO postDTO) {
        // Call repository with the body
        TodoEntity todoEntity = todoShareRepository.persistNewTodo(postDTO);
        // map repository's response to TodoCreationResponse
        return mapToDTO(todoEntity);
    }
}
