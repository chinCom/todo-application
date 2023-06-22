package com.todo.todoapplication.repo;


import com.todo.todoapplication.entities.TodoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServices {
    @Autowired
    TodoRepository repository;

    public Iterable<TodoDB> getAllTodo() {
        return repository.findAll();
    }

    public Optional<TodoDB> findById(Integer id) {
        return repository.findById(id);
    }

    public TodoDB save(TodoDB todo) {
        return repository.save(todo);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public String toString() {
        return String.format("TodoServices [repository=%s]", repository);
    }

}
