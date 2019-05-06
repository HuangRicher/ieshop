package com.seamwhole.serviceuserone;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.seamwhole.serviceuserone.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableDistributedTransaction
@EnableTransactionManagement
public class ServiceUserOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserOneApplication.class, args);
    }

}
