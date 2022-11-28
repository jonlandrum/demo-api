package com.jonlandrum.api;

import com.jonlandrum.api.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableTransactionManagement
@EnableJpaRepositories
public class Application {
	@Autowired
	AccountService accountService;

	@GetMapping("/")
	public String home() {
		return "To use this API, append a GitHub username to the end of the URL:<br>" +
				"<a href='http://localhost:8080/octocat'>http://localhost:8080/octocat</a>";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
