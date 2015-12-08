package com.htf.fmusic.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author HTFeeds
 */
public class Credentials {
    @NotEmpty
    @Size(min = 6)
    private String username;

    @NotEmpty
    @Size(min = 6)
    private String password;

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

}
