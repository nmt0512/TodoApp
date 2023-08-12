package com.nmt.TodoApp.service;

import java.util.List;

import com.nmt.TodoApp.dto.TodoItemDto;

public interface ITodoItemService {
    List<TodoItemDto> findAll();

    Integer getTotalItems();

    TodoItemDto save(TodoItemDto todoItem);

    void deleteById(Long id);

    Integer getTotalActiveItems();

    List<TodoItemDto> findAllByDone(boolean done);

    void toggleDone(Long id);

    void completeAll();

    Integer getTotalCompletedItems();

    void deleteCompletedItems();

}
