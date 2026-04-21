package com.saptarshi.TODO.controller;

import com.saptarshi.TODO.model.Todo;
import com.saptarshi.TODO.repository.TodoRepository;
import com.saptarshi.TODO.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

}
