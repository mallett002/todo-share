package com.task.todoshare.repository;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class TodoShareRepository {

    // Create random id for now until hook up the db
    Random random = new Random();

    public TodoEntity persistNewTodo(TodoEntity postEntity) {
        postEntity.setId(random.nextInt(50));
        return postEntity;
    }
}
