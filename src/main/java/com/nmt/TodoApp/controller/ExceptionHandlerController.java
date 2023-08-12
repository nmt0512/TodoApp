package com.nmt.TodoApp.controller;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BindException.class)
    public String handleNoTodoTitle(BindException e, RedirectAttributes redirectAttr) {
        redirectAttr.addFlashAttribute("exception", true);
        return "redirect:/todo";
    }
}
