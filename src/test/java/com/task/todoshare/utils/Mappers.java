package com.task.todoshare.utils;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import com.task.todoshare.model.UserEntity;

public class Mappers {
    public TodoDTO mapToDTO(TodoEntity todoEntity) {
        TodoDTO dto = new TodoDTO();

        dto.setId(todoEntity.getId());
        dto.setMessage(todoEntity.getMessage());
        dto.setIsCompleted(todoEntity.getIsCompleted());
        dto.setUserId(todoEntity.getUser().getId());
        dto.setIsPrivate(todoEntity.getIsPrivate());
        dto.setDueDate(todoEntity.getDueDate());

        return dto;
    }

    public TodoEntity mapToEntity(TodoDTO dto, UserEntity user) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setId(dto.getId());
        todoEntity.setMessage(dto.getMessage());
        todoEntity.setIsCompleted(dto.getIsCompleted());
        todoEntity.setUser(user);
        todoEntity.setIsPrivate(dto.getIsPrivate());
        todoEntity.setDueDate(dto.getDueDate());

        return todoEntity;
    }
}
