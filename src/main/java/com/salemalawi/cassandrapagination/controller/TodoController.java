package com.salemalawi.cassandrapagination.controller;

import com.salemalawi.cassandrapagination.dto.CassandraPageDto;
import com.salemalawi.cassandrapagination.dto.IdCreate;
import com.salemalawi.cassandrapagination.dto.queryParam.PageInfo;
import com.salemalawi.cassandrapagination.dto.todo.CreateAndUpdateTodoDto;
import com.salemalawi.cassandrapagination.dto.todo.TodoDto;
import com.salemalawi.cassandrapagination.model.Todo;
import com.salemalawi.cassandrapagination.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TodoController {


    @Autowired
    private TodoService todoService;

    @PostMapping("/todo")
    public ResponseEntity createNewTodo(@RequestBody CreateAndUpdateTodoDto createAndUpdateTodoDto) {
        Todo todo = this.todoService.createNewTodo(createAndUpdateTodoDto);
        return new ResponseEntity(new IdCreate(todo.getId()), HttpStatus.OK);
    }


    @GetMapping("/todo")
    public ResponseEntity<CassandraPageDto<TodoDto>> findAllToDo(PageInfo pageInfo) {


        Slice<TodoDto> todoDtoSlice = this.todoService.findAllTodo(pageInfo).map(todo -> this.todoService.toDto(todo));
        return new ResponseEntity(new CassandraPageDto(todoDtoSlice), HttpStatus.OK);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity createNewTodo(@PathVariable("id") UUID todoId) {

        Todo todo = this.todoService.findOneById(todoId);
        return new ResponseEntity(this.todoService.toDto(todo), HttpStatus.OK);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity updateTodo(@PathVariable("id") UUID todoId,
                                     @RequestBody CreateAndUpdateTodoDto createAndUpdateTodoDto) {

        this.todoService.updateTodo(todoId, createAndUpdateTodoDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity removeTodo(@PathVariable("id") UUID todoId) {

        try {
            this.todoService.deleteTodo(todoId);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
