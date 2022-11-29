package com.jonlandrum.api.account;

import com.jonlandrum.api.repo.Repo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a single row of the {@link Account} table
 */
@Entity
public class Account {
    /**
     * Unique ID for this {@link Account}
     * <p>
     *     This had to be named something other than "id" since that field exists in
     *     the GitHub response object, and would overwrite the value in the system.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    /**
     * The username for this {@link Account}
     * <p>
     *     Key in JSON response: "login"
     * </p>
     */
    private String login;
    public String getLogin() { return login; }
    public void setLogin(final String login) { this.login = login; }

    /**
     * The display name for this {@link Account}
     * <p>
     *     Key in JSON response: "name"
     * </p>
     */
    private String name;
    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    /**
     * The URL of the avatar for this {@link Account}
     * <p>
     *     Key in JSON response: "avatar_url"
     * </p>
     */
    private String avatar_url;
    public String getAvatar_url() { return avatar_url; }
    public void setAvatar_url(final String avatar_url) { this.avatar_url = avatar_url; }

    /**
     * The geolocation for this {@link Account}
     * <p>
     *     Key in JSON response: "location"
     * </p>
     */
    private String location;
    public String getLocation() { return location; }
    public void setLocation(final String location) { this.location = location; }

    /**
     * The email address for this {@link Account}
     * <p>
     *     Key in JSON response: "email"
     * </p>
     */
    private String email;
    public String getEmail() { return email; }
    public void setEmail(final String email) { this.email = email; }

    /**
     * The URL to the profile page for this {@link Account}
     * <p>
     *     Key in JSON response: "url"
     * </p>
     */
    private String url;
    public String getUrl() { return url; }
    public void setUrl(final String url) { this.url = url; }

    /**
     * When the GitHub profile was created for this {@link Account}
     * <p>
     *     Key in JSON response: "created_at"
     * </p>
     */
    private String created_at;
    public String getCreated_at() { return created_at; }
    public void setCreated_at(final String created_at) { this.created_at = created_at; }

    /**
     * The last time this user was updated in the system
     * <p>
     *     Will determine if the remote APIs should be queried again before returning results.
     * </p>
     */
    private LocalDateTime updated;
    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated(final LocalDateTime updated) { this.updated = updated; }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", referencedColumnName = "account_id")
    @OrderColumn
    private Repo[] repos;
    public Repo[] getRepos() { return repos; }
    public void setRepos(final Repo[] repos) { this.repos = repos; }

    /**
     * Populated if the user login does not exist at GitHub
     */
    private String message;
    public String getMessage() { return message; }
    public void setMessage(final String message) { this.message = message; }
}
