package com.task.todoshare.repository;

import com.task.todoshare.model.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoShareRepository extends CrudRepository<TodoEntity, Long> {
    // save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
}
