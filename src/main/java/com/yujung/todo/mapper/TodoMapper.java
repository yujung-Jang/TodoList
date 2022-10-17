package com.yujung.todo.mapper;

import com.yujung.todo.dto.TodoDto;
import com.yujung.todo.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostToTodo(TodoDto.Post requestBody);
    Todo todoPatchToTodo(TodoDto.Patch requestBody);
    TodoDto.Response todoTotodoResposne(Todo todo);
    List<TodoDto.Response> todoListTotodoResponse(List<Todo> list);
}
