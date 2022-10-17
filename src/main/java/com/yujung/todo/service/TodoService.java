package com.yujung.todo.service;

import com.yujung.todo.entity.Todo;
import com.yujung.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTitle()).ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getOrder()).ifPresent(order -> findTodo.setOrder(order));
        Optional.ofNullable(todo.isCompleted()).ifPresent(completed -> findTodo.setCompleted(completed));

        return todoRepository.save(findTodo);
    }

    public Todo findTodo(int todoId) {
        return findVerifiedTodo(todoId);
    }

    public List<Todo> findTodoList() {
        return todoRepository.findAll(Sort.unsorted());
    }

    public void deleteTodo(int todoId) {
        Todo findTodo = findVerifiedTodo(todoId);
        todoRepository.delete(findTodo);
    }

    public void deleteTodoList() {
        todoRepository.deleteAll();
    }

    private Todo findVerifiedTodo(int todoId) {

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo findTodo = optionalTodo.orElseThrow(() -> new RuntimeException("TODO_NOT_FOUND"));
        return findTodo;
    }
}
