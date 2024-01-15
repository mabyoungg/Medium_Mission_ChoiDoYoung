package com.ll.likelionspringboottestmedium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LikelionSpringBootTestMediumApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikelionSpringBootTestMediumApplication.class, args);
	}

}
