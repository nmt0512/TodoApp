package com.nmt.TodoApp.converter;

import org.springframework.stereotype.Component;

import com.nmt.TodoApp.dto.TodoItemDto;
import com.nmt.TodoApp.entity.TodoItemEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TodoItemConverter {
    private Timestamp toTimestamp(String todoTime)
    {
        String dateTimeString = todoTime.replace("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return Timestamp.valueOf(dateTime);
    }
    public TodoItemDto toDto(TodoItemEntity itemEntity) {
        TodoItemDto dto = new TodoItemDto();
        dto.setId(itemEntity.getTodoId());
        if (itemEntity.getTodoUser() != null)
            dto.setUserId(itemEntity.getTodoUser().getUserId());
        dto.setTitle(itemEntity.getTitle());
        dto.setDone(itemEntity.isDone());
        dto.setTodoTime(itemEntity.getTodoTime());
        return dto;
    }

    public TodoItemEntity toEntity(TodoItemDto itemDto) {
        TodoItemEntity entity = new TodoItemEntity();
        entity.setTitle(itemDto.getTitle());
        entity.setDone(itemDto.isDone());
        entity.setTodoTime(toTimestamp(itemDto.getTodoTimeStr()));
        return entity;
    }

    public TodoItemEntity toEntity(TodoItemDto itemDto, TodoItemEntity oldEntity) {
        oldEntity.setDone(itemDto.isDone());
        oldEntity.setTitle(itemDto.getTitle());
        if(itemDto.getTodoTimeStr() != null)
            oldEntity.setTodoTime(toTimestamp(itemDto.getTodoTimeStr()));
        return oldEntity;
    }
}
