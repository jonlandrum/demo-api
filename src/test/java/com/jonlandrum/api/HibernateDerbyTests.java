package com.jonlandrum.api;

import com.jonlandrum.api.account.Account;
import com.jonlandrum.api.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.NonUniqueResultException;

@SpringBootTest
public class HibernateDerbyTests {
    @Autowired
    AccountService accountService;

    @Test
    public void testGetUser() {
        try {
            Long id = 1L;
            Account account = accountService.getAccount(id);
            Assertions.assertNull(account);
        } catch (NonUniqueResultException ex) {
            Assertions.fail(ex.toString());
        }
    }
}
