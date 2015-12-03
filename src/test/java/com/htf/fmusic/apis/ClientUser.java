package com.htf.fmusic.apis;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.htf.fmusic.enums.State;

/**
 * @author HTFeeds
 */
public class ClientUser {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private Date birthDate;
    private String phoneNumber;
    private String sex;
    private String imageUrl;
    private String state = State.ACTIVE.getState();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public final Date getBirthDate() {
        return birthDate;
    }

    public final void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public final String getSex() {
        return sex;
    }

    public final void setSex(String sex) {
        this.sex = sex;
    }

    public final String getImageUrl() {
        return imageUrl;
    }

    public final void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public final String getState() {
        return state;
    }

    public final void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("username", this.username).append("fullname", this.fullname)
                .append("email", this.email).toString();
    }
}
