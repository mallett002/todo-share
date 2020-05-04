package com.task.todoshare.services;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import com.task.todoshare.repository.TodoShareRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// hibernate client (uses entityManager)
@Service
public class TodoShareService {

    @Autowired
    TodoShareRepositoryImpl todoShareRepository;

    private TodoDTO mapToDTO(TodoEntity todoEntity) {
        TodoDTO dto = new TodoDTO();

        dto.setId(todoEntity.getId());
        dto.setMessage(todoEntity.getMessage());
        dto.setCompleted(todoEntity.getCompleted());
        dto.setUserId(todoEntity.getUserId());
        dto.setPrivate(todoEntity.getPrivate());
        dto.setDueDate(todoEntity.getDueDate());

        return dto;
    }

    private TodoEntity mapToEntity(TodoDTO dto) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setMessage(dto.getMessage());
        todoEntity.setCompleted(dto.getCompleted());
        todoEntity.setUserId(dto.getUserId());
        todoEntity.setPrivate(dto.getPrivate());
        todoEntity.setDueDate(dto.getDueDate());

        return todoEntity;
    }

    public TodoDTO createTodo(TodoDTO postDTO) {
        TodoEntity entityToPersist = mapToEntity(postDTO);
        TodoEntity persistedEntity = todoShareRepository.save(entityToPersist);

        return mapToDTO(persistedEntity);
    }
}
