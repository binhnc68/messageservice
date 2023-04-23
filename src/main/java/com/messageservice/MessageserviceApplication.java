package com.messageservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessageserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageserviceApplication.class, args);
	}
	
	@Bean
	NewTopic notification() {
		// topic name, partition number, replication number= broker serve
		return new NewTopic("notification", 2, (short) 1);
	}
	
	@Bean
	NewTopic statistic() {
		return new NewTopic("statistic", 1, (short) 1);
	}
	
}
