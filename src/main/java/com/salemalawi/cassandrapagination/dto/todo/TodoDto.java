package com.salemalawi.cassandrapagination.dto.todo;


import com.salemalawi.cassandrapagination.model.enums.TodoStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TodoDto {

    private UUID id;
    private String title;
    private String description;
    private TodoStatusEnum status;
    private LocalDateTime createAt;

}
