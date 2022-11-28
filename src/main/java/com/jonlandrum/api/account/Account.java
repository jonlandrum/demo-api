package com.jonlandrum.api.account;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() { return id; }
    public void setId(final Long id) { this.id = id; }

    // Key in JSON response: "login"
    private String userName;
    public String getUserName() { return userName; }
    public void setUserName(final String userName) { this.userName = userName; }

    // Key in JSON response: "name"
    private String displayName;
    public String getDisplayName() { return displayName; }
    public void setDisplayName(final String displayName) { this.displayName = displayName; }

    // Key in JSON response: "avatar_url"
    private String avatar;
    public String getAvatar() { return avatar; }
    public void setAvatar(final String avatar) { this.avatar = avatar; }

    // Key in JSON response: "location"
    private String geoLocation;
    public String getGeoLocation() { return geoLocation; }
    public void setGeoLocation(final String geoLocation) { this.geoLocation = geoLocation; }

    // Key in JSON response: "email"
    private String email;
    public String getEmail() { return email; }
    public void setEmail(final String email) { this.email = email; }

    // Key in JSON response: "url"
    private String url;
    public String getUrl() { return url; }
    public void setUrl(final String url) { this.url = url; }

    // Key in JSON response: "created_at"
    private String createdAt;
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(final String createdAt) { this.createdAt = createdAt; }

    // The last time this User was updated
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    public Date getUpdated() { return updated; }
    public void setUpdated(final Date updated) { this.updated = updated; }
}
