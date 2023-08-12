package com.nmt.TodoApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nmt.TodoApp.converter.TodoItemConverter;
import com.nmt.TodoApp.dto.TodoItemDto;
import com.nmt.TodoApp.entity.TodoItemEntity;
import com.nmt.TodoApp.entity.TodoUserEntity;
import com.nmt.TodoApp.repository.TodoItemRepository;
import com.nmt.TodoApp.repository.TodoUserRepository;
import com.nmt.TodoApp.service.ITodoItemService;

@Service
public class TodoItemService implements ITodoItemService {
    @Autowired
    TodoItemRepository itemRepository;
    @Autowired
    TodoItemConverter itemConverter;
    @Autowired
    TodoUserRepository userRepository;

    private TodoUserEntity currentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public List<TodoItemDto> findAll() {
        List<TodoItemDto> result = new ArrayList<>();
        List<TodoItemEntity> entities = itemRepository.findAllByTodoUser(currentUser());
        for (TodoItemEntity item : entities) {
            result.add(itemConverter.toDto(item));
        }
        return result;
    }

    @Override
    public Integer getTotalItems() {
        return (int) itemRepository.countAllByTodoUser(currentUser());
    }

    @Override
    public TodoItemDto save(TodoItemDto todoItem) {
        TodoItemEntity entity;
        if (todoItem.getId() == null) {
            todoItem.setDone(false);
            entity = itemConverter.toEntity(todoItem);
        } else {
            TodoItemEntity oldEntity = itemRepository.findById(todoItem.getId()).get();
            entity = itemConverter.toEntity(todoItem, oldEntity);
        }
        entity.setTodoUser(currentUser());
        entity = itemRepository.save(entity);
        return itemConverter.toDto(entity);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Integer getTotalActiveItems() {
        return itemRepository.countAllByDoneAndTodoUser(false, currentUser());
    }

    @Override
    public Integer getTotalCompletedItems() {
        return itemRepository.countAllByDoneAndTodoUser(true, currentUser());
    }

    @Override
    public List<TodoItemDto> findAllByDone(boolean done) {
        List<TodoItemDto> result = new ArrayList<>();
        for (TodoItemEntity item : itemRepository.findAllByDoneAndTodoUser(done, currentUser())) {
            result.add(itemConverter.toDto(item));
        }
        return result;
    }

    @Override
    public void toggleDone(Long id) {
        TodoItemDto item = itemConverter.toDto(itemRepository.findById(id).get());
        item.setDone(!item.isDone());
        save(item);
    }

    @Override
    public void completeAll() {
        for (TodoItemEntity item : itemRepository.findAllByDoneAndTodoUser(false, currentUser())) {
            item.setDone(true);
            itemRepository.save(item);
        }
    }

    @Override
    public void deleteCompletedItems() {
        for (TodoItemEntity item : itemRepository.findAllByDoneAndTodoUser(true, currentUser()))
            itemRepository.deleteById(item.getTodoId());
    }

}
