package com.xiaxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaFinchleyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaFinchleyApplication.class, args);
	}
}
