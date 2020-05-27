package com.task.todoshare.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    private String username;

    private String password;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "userId", cascade = {CascadeType.PERSIST})
    private List<TodoEntity> todos = new ArrayList<>();

    public UserEntity(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public void addTodo(TodoEntity todo) {
        todos.add(todo);
        todo.setUserId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public List<TodoEntity> getTodos() { return todos; }
}
