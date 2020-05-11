package com.task.todoshare.utils;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("Could not find todo item with id " + id);
    }
}
