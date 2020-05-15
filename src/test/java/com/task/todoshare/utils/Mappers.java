package com.task.todoshare.utils;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.model.TodoEntity;

public class Mappers {
    public TodoDTO mapToDTO(TodoEntity todoEntity) {
        TodoDTO dto = new TodoDTO();

        dto.setId(todoEntity.getId());
        dto.setMessage(todoEntity.getMessage());
        dto.setCompleted(todoEntity.getCompleted());
        dto.setUserId(todoEntity.getUserId());
        dto.setPrivate(todoEntity.getPrivate());
        dto.setDueDate(todoEntity.getDueDate());

        return dto;
    }

    public TodoEntity mapToEntity(TodoDTO dto) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setId(dto.getId());
        todoEntity.setMessage(dto.getMessage());
        todoEntity.setCompleted(dto.getCompleted());
        todoEntity.setUserId(dto.getUserId());
        todoEntity.setPrivate(dto.getPrivate());
        todoEntity.setDueDate(dto.getDueDate());

        return todoEntity;
    }
}
