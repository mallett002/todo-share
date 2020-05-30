package com.task.todoshare.dto;

import java.util.ArrayList;
import java.util.List;

public class TodoListResponse {
    private List<TodoDTO> todos;

    public TodoListResponse() {
        todos = new ArrayList<>();
    }

    public void addAll(List<TodoDTO> items) {
        todos.addAll(items);
    }

    public void addTodo(TodoDTO todo) {
        todos.add(todo);
    }
}
