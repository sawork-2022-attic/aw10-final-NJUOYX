package com.pos.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

}
