package com.nmt.TodoApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "todouser")
public class TodoUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String fullname;
    @OneToMany(mappedBy = "todoUser")
    private List<TodoItemEntity> listTodo = new ArrayList<>();

    public Long getUserId() {
        return userId;
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
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<TodoItemEntity> getListTodo() {
        return listTodo;
    }

    public void setListTodo(List<TodoItemEntity> listTodo) {
        this.listTodo = listTodo;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
