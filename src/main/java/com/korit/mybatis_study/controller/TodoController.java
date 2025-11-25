package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.Request.AddTodoReqDto;
import com.korit.mybatis_study.dto.Request.EditTodoReqDto;
import com.korit.mybatis_study.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestBody AddTodoReqDto addTodoReqDto) {
        return ResponseEntity.ok(todoService.addTodo(addTodoReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTodoAll() {
        return ResponseEntity.ok(todoService.getTodoAll());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable Integer todoId) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    @PostMapping("/update")
    public ResponseEntity<?> editTodo(@RequestBody EditTodoReqDto editTodoReqDto) {
        return ResponseEntity.ok(todoService.editTodo(editTodoReqDto));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTodo(@RequestParam Integer todoId) {
        return ResponseEntity.ok(todoService.deleteTodo(todoId));
    }



}
