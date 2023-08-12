package com.nmt.TodoApp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todoitem")
public class TodoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long todoId;
    private String title;
    @Column(name = "time")
    private Timestamp todoTime;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "userid")
    private TodoUserEntity todoUser;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TodoUserEntity getTodoUser() {
        return todoUser;
    }

    public void setTodoUser(TodoUserEntity todoUser) {
        this.todoUser = todoUser;
    }

    public Long getTodoId() {
        return todoId;
    }

    public Timestamp getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(Timestamp todoTime) {
        this.todoTime = todoTime;
    }

}
