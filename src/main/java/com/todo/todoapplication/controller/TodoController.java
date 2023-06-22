package com.todo.todoapplication.controller;


import com.todo.todoapplication.entities.TodoDB;
import com.todo.todoapplication.repo.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TodoController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @Autowired
    private final TodoServices services;

    public TodoController(TodoServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        Iterable<TodoDB> allItems = services.getAllTodo();
        model.addAttribute("todoItems", allItems);
        return "index";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.GET)
    public String addTaskPage() {
        return "addTodo";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(@ModelAttribute TodoDB todoTask) {
        // Capitalize the first character in title column
        StringBuilder title = new StringBuilder(todoTask.getTitle());
        title.setCharAt(0, Character.toUpperCase(title.charAt(0)));//set first character to uppercase

        // Capitalize the first character in description column
        StringBuilder description = new StringBuilder(todoTask.getDescription());
        description.setCharAt(0, Character.toUpperCase(description.charAt(0)));

        todoTask.setTitle(title.toString());
        todoTask.setDescription(description.toString());

        TodoDB todoDB = services.save(todoTask);
        return "redirect:/";
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    public String deleteTask(@RequestParam("id") String id) {
        Optional<TodoDB> todoDB = services.findById(Integer.parseInt(id));
        if (todoDB.isPresent()) {
            services.deleteById(Integer.parseInt(id));
        }
        return "redirect:/";
    }

    // Warning
    @RequestMapping(value = "/deleteAllTask", method = RequestMethod.POST)
    public String deleteAllTask() {
        services.deleteAll();
        return "redirect:/";
    }

    @RequestMapping(value = "/taskCompleted", method = RequestMethod.GET)
    public String taskCompleted(@RequestParam("id") String id) {
        Optional<TodoDB> taskSetComplete = services.findById(Integer.parseInt(id));
        taskSetComplete.ifPresent(todoDB -> {
            todoDB.setComplete(true);
            services.save(todoDB);
        });

        return "redirect:/";
    }


}
