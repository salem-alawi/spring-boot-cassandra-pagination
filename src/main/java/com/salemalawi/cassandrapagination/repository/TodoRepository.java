package com.salemalawi.cassandrapagination.repository;

import com.salemalawi.cassandrapagination.model.Todo;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepository  extends CassandraRepository<Todo, UUID> {
}
