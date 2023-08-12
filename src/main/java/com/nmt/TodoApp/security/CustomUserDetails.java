package com.nmt.TodoApp.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nmt.TodoApp.entity.TodoUserEntity;


public class CustomUserDetails implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    TodoUserEntity userEntity;

    public CustomUserDetails(TodoUserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TodoUserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(TodoUserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
