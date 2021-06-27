package com.salemalawi.cassandrapagination.model;


import com.salemalawi.cassandrapagination.dto.todo.CreateAndUpdateTodoDto;
import com.salemalawi.cassandrapagination.model.enums.TodoStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@NoArgsConstructor
@Data
public class Todo {


    @PrimaryKey
    private UUID id;
    private String title;
    private String description;
    private TodoStatusEnum status;


    public Todo(CreateAndUpdateTodoDto createAndUpdateTodoDto) {
        this.setId(UUID.randomUUID());
        this.setTitle(createAndUpdateTodoDto.getTitle());
        this.setDescription(createAndUpdateTodoDto.getDescription());
        this.setStatus(TodoStatusEnum.NOT_DONE);
    }


    public static Todo NotFound() {
        Todo todo = new Todo();
        todo.setTitle("NOT_FOUND");
        todo.setTitle("NOT_FOUND");
        todo.setStatus(TodoStatusEnum.NOT_FOUND);
        return todo;
    }

    public void update(CreateAndUpdateTodoDto createAndUpdateTodoDto) {
        // note update should not include primary key or partition key  because cassandra insert and update is equal to we don't update id or create_date

        this.setDescription(createAndUpdateTodoDto.getDescription());
        this.setTitle(createAndUpdateTodoDto.getTitle());

    }

    public void done() {
        this.setStatus(TodoStatusEnum.DONE);
    }

    public void notDone() {
        this.setStatus(TodoStatusEnum.NOT_DONE);
    }
}
