package com.nmt.TodoApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nmt.TodoApp.entity.TodoUserEntity;
import com.nmt.TodoApp.repository.TodoUserRepository;
import com.nmt.TodoApp.security.CustomUserDetails;

@Service
public class TodoUserService implements UserDetailsService {

    @Autowired
    TodoUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //check the username is exist on database or not

        TodoUserEntity user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new CustomUserDetails(user);
    }
}
