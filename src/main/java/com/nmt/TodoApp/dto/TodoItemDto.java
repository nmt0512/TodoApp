package com.nmt.TodoApp.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TodoItemDto {
    private Long id;
    @NotBlank
    private String title;
    private Long userId;
    private String todoTimeStr;
    private Timestamp todoTime;
    private boolean done;
}
