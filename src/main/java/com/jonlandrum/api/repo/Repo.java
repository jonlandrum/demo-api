package com.jonlandrum.api.repo;

import com.jonlandrum.api.account.Account;

import javax.persistence.*;

@Entity
public class Repo {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    public Account getAccount() { return account; }
    public void setAccount(final Account account) { this.account = account; }

    // Key in JSON response: "name"
    private String name;
    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    // Key in JSON response: "html_url"
    private String url;
    public String getUrl() { return url; }
    public void setUrl(final String url) { this.url = url; }
}
