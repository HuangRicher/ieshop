package com.seamwhole.serviceuaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.seamwhole.serviceuaa.mapper")
@EnableEurekaClient
@SpringBootApplication
public class ServiceUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUaaApplication.class, args);
    }

}
