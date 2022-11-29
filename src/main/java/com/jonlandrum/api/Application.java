package com.jonlandrum.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableTransactionManagement
@EnableJpaRepositories
public class Application {
	@GetMapping("/")
	public String home() {
		return "To use this API, append a GitHub username to the end of the URL:<br>" +
				"<a href='http://localhost:8080/octocat'>http://localhost:8080/octocat</a>";
	}

	/**
	 * Represents a response from a consumed REST API
	 * @return {@link RestTemplate} to use for the API response
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
