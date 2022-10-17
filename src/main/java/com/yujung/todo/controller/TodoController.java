package com.yujung.todo.controller;

import com.yujung.todo.dto.TodoDto;
import com.yujung.todo.entity.Todo;
import com.yujung.todo.mapper.TodoMapper;
import com.yujung.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/todo")
@Validated
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    // 할 일 등록
    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody) {

        Todo todo = mapper.todoPostToTodo(requestBody); // 요청 Dto -> 엔티티

        Todo createTodo = todoService.createTodo(todo);
        TodoDto.Response response = mapper.todoTotodoResposne(createTodo); // 엔티티 -> 응답 Dto

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 할 일 수정
    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive int todoId,
                                    @Valid @RequestBody TodoDto.Patch requestBody) {

        requestBody.setId(todoId);
        Todo todo = mapper.todoPatchToTodo(requestBody);
        Todo updateTodo = todoService.updateTodo(todo);

        TodoDto.Response response = mapper.todoTotodoResposne(updateTodo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 특정 할 일 조회
    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive int todoId) {
        Todo todo = todoService.findTodo(todoId);
        TodoDto.Response response = mapper.todoTotodoResposne(todo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 모든 할 일 조회
    @GetMapping
    public ResponseEntity getTodoList() {

        List<Todo> todoList = todoService.findTodoList();
        List<TodoDto.Response> responses = mapper.todoListTotodoResponse(todoList);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // 할 일 삭제
    @DeleteMapping("/{todo-id}")
    public void deleteTodo(@Positive @PathVariable("todo-id") int todoId) {
        todoService.deleteTodo(todoId);
    }

    // 리스트의 모든 할 일 삭제
    @DeleteMapping
    public void deleteTodoList() {
        todoService.deleteTodoList();
    }
}
