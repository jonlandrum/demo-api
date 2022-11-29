package com.jonlandrum.api.account;

public class AccountNotFoundException extends RuntimeException {
    AccountNotFoundException(final String username) {
        super("Account \"" + username + "\" does not exist");
    }
}
