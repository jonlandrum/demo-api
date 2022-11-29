package com.jonlandrum.api.account;

import com.jonlandrum.api.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AccountController {
    @Autowired
    private ApplicationContext context;

    private final AccountRepository repository;

    AccountController(final AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{login}")
    public EntityModel<Account> getAccount(@PathVariable final String login) {
        Account account;
        var potentialAccount = repository.findByLogin(login);
        if(potentialAccount.isPresent()) {
            account = potentialAccount.get();
            if (shouldUpdate(account)) {
                updateAccount(account);
            }
        } else {
            if(userExists(login)) {
                account = new Account();
                account.setLogin(login);
                account = updateAccount(account);
            } else
                throw new AccountNotFoundException(login);
        }
        return EntityModel.of(account,
                linkTo(methodOn(AccountController.class).getAccount(login)).withSelfRel());
    }

    /**
     * Whether the specified username is a valid account on GitHub
     * @param username String representing the username to test
     * @return true if the user exists on GitHub
     */
    private boolean userExists(final String username) {
        var accountEndpoint = "https://api.github.com/users/" + username;
        var restTemplate = new RestTemplate();
        var accountResponse = restTemplate.getForObject(accountEndpoint, Account.class);
        return (accountResponse != null && accountResponse.getMessage() == null);
    }

    /**
     * Whether the {@link Account} is due to be updated
     * @param account reference to the {@link Account} to query
     * @return true if the current time is after the update timeout
     */
    private boolean shouldUpdate(final Account account) {
        var current = LocalDateTime.now();
        var delay = Duration.ofMinutes(5);
        if(account.getUpdated() != null) {
            var updateTime = account.getUpdated().plus(delay);
            return current.isAfter(updateTime);
        } else
            return true;
    }

    private Account updateAccount(final Account account) {
        var accountEndpoint = "https://api.github.com/users/" + account.getLogin();
        var reposEndpoint = accountEndpoint + "/repos";
        var restTemplate = new RestTemplate();
        var accountResponse = restTemplate.getForObject(accountEndpoint, Account.class);
        var reposResponse = restTemplate.getForEntity(reposEndpoint, Repo[].class);
        var repos = reposResponse.getBody();

        return repository.findByLogin(account.getLogin())
                .map(acct -> {
                    if(accountResponse != null) {
                        accountResponse.setLogin(account.getLogin());
                        accountResponse.setName(account.getName());
                        accountResponse.setAvatar_url(account.getAvatar_url());
                        accountResponse.setLocation(account.getLocation());
                        accountResponse.setEmail(account.getEmail());
                        accountResponse.setUrl(account.getUrl());
                        accountResponse.setCreated_at(account.getCreated_at());
                        accountResponse.setUpdated(LocalDateTime.now());
                        accountResponse.setRepos(repos);
                        return repository.save(accountResponse);
                    } else
                        return account;
                }).orElseGet(() -> {
                    if (accountResponse != null) {
                        accountResponse.setLogin(account.getLogin());
                        accountResponse.setRepos(repos);
                        return repository.save(accountResponse);
                    } else
                        return account;
                });
    }
}
