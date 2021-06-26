package com.salemalawi.cassandrapagination.dto.queryParam;

import com.datastax.oss.protocol.internal.util.Bytes;
import lombok.Data;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.nio.ByteBuffer;

@Data
public class PageInfo {


    private String requestPageState;
    private Integer size;


    public CassandraPageRequest getPageable() {
        if (this.size == null)
            this.size = 20;
        Pageable pageable = PageRequest.of(0, this.size);
        if (this.requestPageState == null || this.requestPageState.trim().equals(""))
            return CassandraPageRequest.first(pageable.getPageSize());
        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = Bytes.fromHexString(this.requestPageState);
        } catch (Exception e) {
            return CassandraPageRequest.first(pageable.getPageSize());
        }
        return CassandraPageRequest.of(pageable, byteBuffer);
    }

}
