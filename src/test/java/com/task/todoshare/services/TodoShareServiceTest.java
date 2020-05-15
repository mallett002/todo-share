package com.task.todoshare.services;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import com.task.todoshare.repository.TodoShareRepository;
import com.task.todoshare.utils.Mappers;
import com.task.todoshare.utils.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TodoShareServiceTest {

    RandomGenerator generator = new RandomGenerator();
    Mappers mappers = new Mappers();

    @InjectMocks
    TodoShareService todoShareService;

    @Mock
    TodoShareRepository todoShareRepository;

    @Test
    public void should_find_todo_by_id() {
        Long id = 1L;
        TodoEntity entity = new TodoEntity();
        entity.setId(id);
        Optional<TodoEntity> optional = Optional.of(entity);

        when(todoShareRepository.findById(id))
                .thenReturn(optional);

        TodoDTO actual = todoShareService.findById(id);

        assertEquals(id, actual.getId());
    }

    @Test
    public void when_create_todo_should_save_into_database() {
        TodoDTO dto = generator.buildNewTodoDTO();
        TodoEntity entity = mappers.mapToEntity(dto);

        when(todoShareRepository.save(any(TodoEntity.class)))
            .thenReturn(entity);

        TodoDTO actual = todoShareService.createTodo(dto);

        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getMessage(), actual.getMessage());
    }

    @Test
    public void should_update_todo_with_provided_todo() {
        TodoDTO currentDto = generator.buildNewTodoDTO();
        currentDto.setMessage(generator.createRandomString());
        Long id = currentDto.getId();

        String expectedMessage = generator.createRandomString();
        TodoDTO newDto = currentDto;
        newDto.setMessage(expectedMessage);

        Optional<TodoEntity> foundEntity = Optional.of(mappers.mapToEntity(currentDto));
        TodoEntity persistedEntity = mappers.mapToEntity(newDto);

        when(todoShareRepository.findById(id))
            .thenReturn(foundEntity);

        when(todoShareRepository.save(any(TodoEntity.class)))
            .thenReturn(persistedEntity);

        TodoDTO actual = todoShareService.updateTodo(id, newDto);

        assertEquals(id, actual.getId());
        assertEquals(expectedMessage, actual.getMessage());
    }

    @Test
    public void should_delete_todo_by_id() {
        Long id = generator.getRandomLong();
        TodoEntity foundEntity = new TodoEntity();
        foundEntity.setId(id);

        Optional<TodoEntity> optionalEntity = Optional.of(foundEntity);

        when(todoShareRepository.findById(id))
            .thenReturn(optionalEntity);

        Long actual = todoShareService.deleteTodo(id);

        verify(todoShareRepository, times(1)).deleteById(id);
        assertEquals(id, actual);
    }
}
