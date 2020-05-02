package com.task.todoshare.repository;

import com.task.todoshare.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TodoShareRepository {
    public TodoEntity persistNewTodo(TodoDTO postDTO) {
        return new TodoEntity();
    }
}
