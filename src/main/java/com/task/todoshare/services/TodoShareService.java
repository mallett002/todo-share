package com.task.todoshare.services;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.dto.TodoListResponse;
import com.task.todoshare.model.TodoEntity;
import com.task.todoshare.model.UserEntity;
import com.task.todoshare.repository.TodoShareRepository;
import com.task.todoshare.utils.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TodoShareService {

    @Autowired
    TodoShareRepository todoShareRepository;

    @Autowired
    UserInfoService userInfoService;

    private TodoDTO mapToDTO(TodoEntity todoEntity) {
        TodoDTO dto = new TodoDTO();

        dto.setId(todoEntity.getId());
        dto.setMessage(todoEntity.getMessage());
        dto.setIsCompleted(todoEntity.getIsCompleted());
        dto.setUserId(todoEntity.getUser().getId());
        dto.setIsPrivate(todoEntity.getIsPrivate());
        dto.setDueDate(todoEntity.getDueDate());

        return dto;
    }

    private TodoEntity mapToEntity(TodoDTO dto, UserEntity user) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setMessage(dto.getMessage());
        todoEntity.setIsCompleted(dto.getIsCompleted());
        todoEntity.setUser(user);
        todoEntity.setIsPrivate(dto.getIsPrivate());
        todoEntity.setDueDate(dto.getDueDate());

        return todoEntity;
    }

    public TodoDTO createTodo(TodoDTO postDTO) {
        UserEntity user = userInfoService.findUserById(postDTO.getUserId());
        TodoEntity todoEntity = mapToEntity(postDTO, user);

        TodoEntity persistedTodoEntity = todoShareRepository.save(todoEntity);

        return mapToDTO(persistedTodoEntity);
    }

    public TodoListResponse getAllPublicTodos() {
        Iterable<TodoEntity> todoEntities = todoShareRepository.findAll();
        TodoListResponse response = new TodoListResponse();

        StreamSupport.stream(todoEntities.spliterator(), false)
            .filter((todo) -> !todo.getIsPrivate())
            .map(this::mapToDTO)
            .forEach(response::addTodo);

        return response;
    }

    public TodoListResponse getTodosForUser(Long userId) {
        UserEntity user = userInfoService.findUserById(userId);
        TodoListResponse response = new TodoListResponse();

        user.getTodos().stream()
            .map(this::mapToDTO)
            .forEach(response::addTodo);

        return response;
    }

    public TodoDTO findById(Long id) {
        TodoEntity todoEntity = todoShareRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));

        return mapToDTO(todoEntity);
    }

    public TodoDTO updateTodo(Long id, TodoDTO updateDTO) {
        TodoEntity todoEntity = todoShareRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoEntity.setMessage(updateDTO.getMessage());
        todoEntity.setIsCompleted(updateDTO.getIsCompleted());
        todoEntity.setIsPrivate(updateDTO.getIsPrivate());
        todoEntity.setDueDate(updateDTO.getDueDate());

        TodoEntity persistedEntity = todoShareRepository.save(todoEntity);

        return mapToDTO(persistedEntity);
    }

    public Long deleteTodo(Long id) {
        Long foundId = todoShareRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id)).getId();

        todoShareRepository.deleteById(foundId);

        return foundId;
    }
}
