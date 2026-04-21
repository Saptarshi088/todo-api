package com.saptarshi.TODO.service;

import com.saptarshi.TODO.model.Todo;
import com.saptarshi.TODO.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo){
        if(todo.getTitle() == null || todo.getDueDate() == null) {
            return null;
        }
        return todoRepository.save(todo);
    }
}
