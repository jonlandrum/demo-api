package com.jonlandrum.api;

import com.jonlandrum.api.account.AccountController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.EntityModel;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootApplication
@RestController
@EnableTransactionManagement
@EnableJpaRepositories
public class Application {
	@GetMapping("/")
	public EntityModel<Home> home() {
		return EntityModel.of(new Home(), linkTo(methodOn(AccountController.class).getAccount("octocat")).withSelfRel());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
