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
import org.mockito.Mockito;
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
    public void findByIdTest() {
        Long id = 1L;
        TodoEntity entity = new TodoEntity();
        entity.setId(id);
        Optional<TodoEntity> optional = Optional.of(entity);

        Mockito.when(todoShareRepository.findById(id))
                .thenReturn(optional);

        TodoDTO actual = todoShareService.findById(id);

        assertEquals(id, actual.getId());
    }

    @Test
    public void when_create_todo_should_save_into_database() throws Exception {
        TodoDTO dto = generator.buildNewTodoDTO();
        TodoEntity entity = mappers.mapToEntity(dto);

        Mockito.when(todoShareRepository.save(Mockito.any(TodoEntity.class)))
            .thenReturn(entity);

        TodoDTO actual = todoShareService.createTodo(dto);

        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getMessage(), actual.getMessage());
    }

}
