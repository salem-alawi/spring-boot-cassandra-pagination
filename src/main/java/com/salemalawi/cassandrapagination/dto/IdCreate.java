package com.salemalawi.cassandrapagination.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class IdCreate {

    private UUID id;

    public IdCreate(UUID id) {

        this.id=id;
    }
}
