package com.nmt.TodoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmt.TodoApp.entity.TodoUserEntity;

public interface TodoUserRepository extends JpaRepository<TodoUserEntity, Long> {
    TodoUserEntity findByUsername(String username);
}
