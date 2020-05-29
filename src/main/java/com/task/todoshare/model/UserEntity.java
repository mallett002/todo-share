package com.task.todoshare.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    private Set<TodoEntity> todos = new HashSet<>();

    public UserEntity() {}

    public UserEntity(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public void addTodo(TodoEntity todo) {
        todos.add(todo);
        todo.setUser(this);
    }

    public Set<TodoEntity> getTodos() {return this.todos;}

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", todos=" + todos +
                '}';
    }
}
