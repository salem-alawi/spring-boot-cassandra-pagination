package com.salemalawi.cassandrapagination;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;

@SpringBootApplication
public class CassandraPaginationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraPaginationApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapper(){
	    return new ModelMapper();
    }

}
