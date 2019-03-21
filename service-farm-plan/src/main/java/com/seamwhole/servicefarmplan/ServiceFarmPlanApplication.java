package com.seamwhole.servicefarmplan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.seamwhole.servicefarmplan.mapper")
@EnableEurekaClient
@SpringBootApplication
public class ServiceFarmPlanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFarmPlanApplication.class, args);
    }

}
