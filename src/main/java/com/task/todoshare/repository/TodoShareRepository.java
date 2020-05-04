package com.task.todoshare.repository;

import com.task.todoshare.dto.TodoDTO;
import com.task.todoshare.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<TodoShareRepository, Long> Do this once have all crud methods
public interface TodoShareRepository {
    public TodoEntity save(TodoEntity todo);
}
