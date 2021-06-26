package com.salemalawi.cassandrapagination.dto;

import com.datastax.oss.protocol.internal.util.Bytes;
import lombok.Data;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;

import java.nio.ByteBuffer;
import java.util.List;

@Data
public class CassandraPageDto<T> {

    private final Integer size;
    private final Boolean hasNext;
    private String nextPageState;
    private final List<T> content;

    public CassandraPageDto(final Slice<T> slice) {
        this.content = slice.getContent();
        this.size = content.size();
        this.hasNext = slice.hasNext();

        if (this.hasNext) {
            CassandraPageRequest pageRequest = (CassandraPageRequest) slice.nextPageable();
            ByteBuffer bytes = pageRequest.getPagingState();

            this.nextPageState = Bytes.toHexString(bytes);
        } else {
            this.nextPageState = null;
        }
    }

}
