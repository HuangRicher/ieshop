package com.seamwhole.nettyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class NettyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyServerApplication.class, args);
	}

}
