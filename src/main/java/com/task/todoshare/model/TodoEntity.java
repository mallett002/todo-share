package com.task.todoshare.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    Long id;

    @Column(name = "message")
    String message;

    @Column(name = "completed")
    boolean isCompleted;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    Long userId;

    @Column(name = "private")
    boolean isPrivate;

    @Column(name = "due_date")
    String dueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", isCompleted=" + isCompleted +
                ", userId='" + userId + '\'' +
                ", isPrivate=" + isPrivate +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
