package com.jonlandrum.api.repo;

import com.jonlandrum.api.account.Account;

import javax.persistence.*;

@Entity
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repo_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    public Account getAccount() { return account; }
    public void setAccount(final Account account) { this.account = account; }

    // Key in JSON response: "name"
    private String name;
    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    // Key in JSON response: "html_url"
    private String html_url;
    public String getHtml_url() { return html_url; }
    public void setHtml_url(final String html_url) { this.html_url = html_url; }
}
