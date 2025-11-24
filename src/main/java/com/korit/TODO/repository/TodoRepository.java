package com.korit.TODO.repository;

import com.korit.TODO.entity.Todo;
import com.korit.TODO.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    @Autowired
    private TodoMapper todoMapper;

    public Optional<Todo> getTitle (String title) {
        return todoMapper.getTitle(title);
    }

    public Optional<Todo> addTodo(Todo todo) {
        try {
            todoMapper.addTodo(todo);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(todo);
    }

    public List<Todo> getTodoAll() {
        return todoMapper.getTodoAll();
    }

    public Optional<Todo> getTodo(Integer todoId) {
        return todoMapper.getTodo(todoId);
    }

    public int editTodo (Todo todo) {
        return todoMapper.editTodo(todo);
    }

    public int deleteTodo (Integer todoId) {
        return todoMapper.deleteTodo(todoId);
    }

}
