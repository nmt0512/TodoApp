package com.nmt.TodoApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nmt.TodoApp.service.impl.TodoUserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TodoUserService todoUserService;

//	@Bean
//    public PasswordEncoder passwordEncoder() {
//		//encode user's password
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(todoUserService) //provide UserService for Spring Security
                .passwordEncoder(passwordEncoder()); //provide password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/todo/**")
                .authenticated()
                .and()
                .formLogin()  //allow everyone to authenticate by login form
                .defaultSuccessUrl("/todo")
                .permitAll()
                .and()
                .logout()  //allow everyone to logout
                .permitAll();
    }

}
