package com.nmt.TodoApp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nmt.TodoApp.dto.TodoItemDto;
import com.nmt.TodoApp.filter.ListFilter;
import com.nmt.TodoApp.service.ITodoItemService;

@Controller
@RequestMapping("/todo")
public class HomeController {
    @Autowired
    ITodoItemService itemService;

    @GetMapping
    public String getTodoHome(Model model) {
        addAttribute(model, ListFilter.ALL);
        return "todo";
    }

    @GetMapping("/active")
    public String getActiveTodo(Model model) {
        addAttribute(model, ListFilter.ACTIVE);
        return "todo";
    }

    @GetMapping("/completed")
    public String getCompletedTodo(Model model) {
        addAttribute(model, ListFilter.COMPLETED);
        return "todo";
    }

    private void addAttribute(Model model, ListFilter filter) {
        List<TodoItemDto> itemList = new ArrayList<>();
        model.addAttribute("todoItem", new TodoItemDto());
        model.addAttribute("totalItems", itemService.getTotalItems());
        model.addAttribute("totalActiveItems", itemService.getTotalActiveItems());
        model.addAttribute("totalCompletedItems", itemService.getTotalCompletedItems());
        model.addAttribute("filter", filter);
        if (filter.name().equals("ALL"))
            itemList = itemService.findAll();
        else if (filter.name().equals("ACTIVE"))
            itemList = itemService.findAllByDone(false);
        else if (filter.name().equals("COMPLETED"))
            itemList = itemService.findAllByDone(true);
        model.addAttribute("todos", itemList);
    }

    @PostMapping
    public String saveTodo(@Valid @ModelAttribute("todoItem") TodoItemDto todoItem) {
        itemService.save(todoItem);
        return "redirect:/todo";
    }

    @PutMapping("/{id}/toggle")
    public String toggleSelection(@PathVariable("id") Long id) {
        itemService.toggleDone(id);
        return "redirect:/todo";
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        itemService.deleteById(id);
        return "redirect:/todo";
    }

    @PutMapping("/complete-all")
    public String completeAll() {
        itemService.completeAll();
        return "redirect:/todo";
    }

    @DeleteMapping("/clear-completed")
    public String clearCompletedTodo() {
        itemService.deleteCompletedItems();
        return "redirect:/todo";
    }

}
