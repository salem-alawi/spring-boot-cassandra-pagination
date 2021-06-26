package com.salemalawi.cassandrapagination.service;

import com.salemalawi.cassandrapagination.dto.queryParam.PageInfo;
import com.salemalawi.cassandrapagination.dto.todo.CreateAndUpdateTodoDto;
import com.salemalawi.cassandrapagination.dto.todo.TodoDto;
import com.salemalawi.cassandrapagination.model.Todo;
import com.salemalawi.cassandrapagination.model.enums.TodoStatusEnum;
import com.salemalawi.cassandrapagination.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    public TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Slice<Todo> findAllTodo(PageInfo pageInfo) {
        return this.todoRepository.findAll(pageInfo.getPageable());
    }

    public Todo createNewTodo(CreateAndUpdateTodoDto createAndUpdateTodoDto) {

        Todo todo = new Todo(createAndUpdateTodoDto);
        return this.todoRepository.save(todo);
    }

    public void updateTodo(UUID todoId, CreateAndUpdateTodoDto createAndUpdateTodoDto) {

        Todo todo = this.findOneById(todoId);
        todo.update(createAndUpdateTodoDto);
        this.todoRepository.save(todo);
    }

    public void deleteTodo(UUID todoId) {
        Todo todo = this.findOneById(todoId);
        if(todo.getStatus()== TodoStatusEnum.NOT_FOUND|| todo.getId()==null)
            throw new RuntimeException("can't find todo");

        // hard delete  or in production should be like  todo.remove() this will change isActive = false
        this.todoRepository.delete(todo);
    }


    public TodoDto toDto(Todo todo) {
        return this.modelMapper.map(todo, TodoDto.class);
    }

    public Todo findOneById(UUID todoId) {
        return this.todoRepository.findById(todoId).orElse(Todo.NotFound());
    }
}
