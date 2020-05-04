package com.task.todoshare.repository;

import com.task.todoshare.model.TodoEntity;
import org.springframework.stereotype.Repository;


@Repository
public class TodoShareRepositoryImpl implements TodoShareRepository {

    public TodoEntity save(TodoEntity todo) {
        return todo;
    }
}
