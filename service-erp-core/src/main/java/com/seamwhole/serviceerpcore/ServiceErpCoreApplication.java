package com.seamwhole.serviceerpcore;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.seamwhole.serviceerpcore.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableDistributedTransaction
@EnableTransactionManagement
public class ServiceErpCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceErpCoreApplication.class, args);
    }

}
