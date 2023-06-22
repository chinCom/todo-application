package com.todo.todoapplication.repo;

import com.todo.todoapplication.entities.TodoDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoDB, Integer> {
}
