package com.Lib.Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LibBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibBackApplication.class, args);
	}

}
