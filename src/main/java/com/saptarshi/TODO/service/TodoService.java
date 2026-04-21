package com.saptarshi.TODO.service;

import com.saptarshi.TODO.model.Todo;
import com.saptarshi.TODO.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Cacheable(value = "todos", key = "'all'")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    @Cacheable(value = "todo", key = "#id")
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @CacheEvict(value = "todos", key = "'all'")
    public Todo createTodo(Todo todo){
        if (todo == null || todo.getTitle() == null || todo.getTitle().isBlank() || todo.getDueDate() == null) {
            return null;
        }

        todo.setId(null);
        return todoRepository.save(todo);
    }

    @CachePut(value = "todo", key = "#todo.id")
    @CacheEvict(value = "todos", key = "'all'")
    public Todo updateTodo(Todo todo) {
        if (todo == null || todo.getId() == null) {
            return null;
        }
        return todoRepository.save(todo);
    }

    @Caching(evict = {
        @CacheEvict(value = "todo", key = "#id"),
        @CacheEvict(value = "todos", key = "'all'")
    })
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
