package com.htf.fmusic.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;
import com.htf.fmusic.annotations.PhoneNumber;
import com.htf.fmusic.enums.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "users")
public final class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    protected static final int MAX_LENGTH_USER = 64;
    protected static final int MAX_LENGTH_PASSWORD = 100;
    protected static final int MAX_LENGTH_EMAIL = 100;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Size(min = 6)
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Size(min = 6, max = MAX_LENGTH_USER)
    @Column(name = "username", unique = true, nullable = false, length = MAX_LENGTH_USER)
    private String username;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Size(min = 6, max = MAX_LENGTH_PASSWORD)
    @Column(name = "password", nullable = false, length = MAX_LENGTH_PASSWORD)
    private String password;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Email
    @Size(min = 6, max = MAX_LENGTH_EMAIL)
    @Column(name = "email", unique = true, nullable = false, length = MAX_LENGTH_EMAIL)
    private String email;

    @JsonView(Views.ExtendedPublic.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_dt")
    private Date birthDate;

    @JsonView(Views.ExtendedPublic.class)
    @PhoneNumber
    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "sex")
    private String sex;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "image_url")
    private String imageUrl = "/static/img/user/avatar_default.jpg";

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "state", nullable = false)
    private String state = State.ACTIVE.getState();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_mappings", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_playlist_mappings", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "playlist_id") })
    private Set<Playlist> playlists = new HashSet<Playlist>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public final Date getBirthDate() {
        return birthDate;
    }

    public final void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void update(String newFullname, String newEmail, Date newBirthDate, String newPhoneNumber, String newSex, String newState,
            Set<Role> newRoles) {
        this.fullname = newFullname;
        this.email = newEmail;
        this.birthDate = newBirthDate;
        this.phoneNumber = newPhoneNumber;
        this.sex = newSex;
        this.state = newState;
        this.roles = newRoles;
    }

    public boolean addPlaylist(Playlist newPlaylist) {
        if (this.playlists.contains(newPlaylist)) {
            return false;
        }
        this.playlists.add(newPlaylist);
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("username", this.username).append("fullname", this.fullname)
                .append("email", this.email).append("state", this.state).toString();
    }
}
