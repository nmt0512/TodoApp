package com.nmt.TodoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmt.TodoApp.entity.TodoItemEntity;
import com.nmt.TodoApp.entity.TodoUserEntity;

public interface TodoItemRepository extends JpaRepository<TodoItemEntity, Long> {
    Integer countAllByDoneAndTodoUser(boolean done, TodoUserEntity todoUser);

    List<TodoItemEntity> findAllByDoneAndTodoUser(boolean done, TodoUserEntity todoUser);

    List<TodoItemEntity> findAllByTodoUser(TodoUserEntity todoUser);

    Integer countAllByTodoUser(TodoUserEntity todoUser);
}
