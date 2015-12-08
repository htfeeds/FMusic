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
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "users")
public final class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    private String imageUrl;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "state", nullable = false)
    private String state = State.ACTIVE.getState();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_mappings", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<Role>();

    @OneToMany(mappedBy = "createdByUser")
    private List<Song> createdSongs = new ArrayList<Song>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<Song> modifiedSongs = new ArrayList<Song>();

    @OneToMany(mappedBy = "createdByUser")
    private List<Playlist> createdPlaylists = new ArrayList<Playlist>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<Playlist> modifiedPlaylists = new ArrayList<Playlist>();

    @OneToMany(mappedBy = "createdByUser")
    private List<PlaylistType> createdPlaylistTypes = new ArrayList<PlaylistType>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<PlaylistType> modifiedPlaylistTypes = new ArrayList<PlaylistType>();

    @OneToMany(mappedBy = "createdByUser")
    private List<Genre> createdGenres = new ArrayList<Genre>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<Genre> modifiedGenres = new ArrayList<Genre>();

    @OneToMany(mappedBy = "createdByUser")
    private List<Lyric> createdLyrics = new ArrayList<Lyric>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<Lyric> modifiedLyrics = new ArrayList<Lyric>();

    @OneToMany(mappedBy = "createdByUser")
    private List<Artist> createdArtists = new ArrayList<Artist>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<Artist> modifiedArtists = new ArrayList<Artist>();

    @OneToMany(mappedBy = "createdByUser")
    private List<Role> createdRoles = new ArrayList<Role>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<Role> modifiedRoles = new ArrayList<Role>();

    @OneToMany(mappedBy = "createdByUser")
    private List<User> createdUsers = new ArrayList<User>();

    @OneToMany(mappedBy = "modifiedByUser")
    private List<User> modifiedUsers = new ArrayList<User>();

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

    public List<Song> getCreatedSongs() {
        return createdSongs;
    }

    public void setCreatedSongs(List<Song> createdSongs) {
        this.createdSongs = createdSongs;
    }

    public List<Song> getModifiedSongs() {
        return modifiedSongs;
    }

    public void setModifiedSongs(List<Song> modifiedSongs) {
        this.modifiedSongs = modifiedSongs;
    }

    public List<Playlist> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public void setCreatedPlaylists(List<Playlist> createdPlaylists) {
        this.createdPlaylists = createdPlaylists;
    }

    public List<Playlist> getModifiedPlaylists() {
        return modifiedPlaylists;
    }

    public void setModifiedPlaylists(List<Playlist> modifiedPlaylists) {
        this.modifiedPlaylists = modifiedPlaylists;
    }

    public List<PlaylistType> getCreatedPlaylistTypes() {
        return createdPlaylistTypes;
    }

    public void setCreatedPlaylistTypes(List<PlaylistType> createdPlaylistTypes) {
        this.createdPlaylistTypes = createdPlaylistTypes;
    }

    public List<PlaylistType> getModifiedPlaylistTypes() {
        return modifiedPlaylistTypes;
    }

    public void setModifiedPlaylistTypes(List<PlaylistType> modifiedPlaylistTypes) {
        this.modifiedPlaylistTypes = modifiedPlaylistTypes;
    }

    public List<Genre> getCreatedGenres() {
        return createdGenres;
    }

    public void setCreatedGenres(List<Genre> createdGenres) {
        this.createdGenres = createdGenres;
    }

    public List<Genre> getModifiedGenres() {
        return modifiedGenres;
    }

    public void setModifiedGenres(List<Genre> modifiedGenres) {
        this.modifiedGenres = modifiedGenres;
    }

    public List<Lyric> getCreatedLyrics() {
        return createdLyrics;
    }

    public void setCreatedLyrics(List<Lyric> createdLyrics) {
        this.createdLyrics = createdLyrics;
    }

    public List<Lyric> getModifiedLyrics() {
        return modifiedLyrics;
    }

    public void setModifiedLyrics(List<Lyric> modifiedLyrics) {
        this.modifiedLyrics = modifiedLyrics;
    }

    public List<Artist> getCreatedArtists() {
        return createdArtists;
    }

    public void setCreatedArtists(List<Artist> createdArtists) {
        this.createdArtists = createdArtists;
    }

    public List<Artist> getModifiedArtists() {
        return modifiedArtists;
    }

    public void setModifiedArtists(List<Artist> modifiedArtists) {
        this.modifiedArtists = modifiedArtists;
    }

    public List<Role> getCreatedRoles() {
        return createdRoles;
    }

    public void setCreatedRoles(List<Role> createdRoles) {
        this.createdRoles = createdRoles;
    }

    public List<Role> getModifiedRoles() {
        return modifiedRoles;
    }

    public void setModifiedRoles(List<Role> modifiedRoles) {
        this.modifiedRoles = modifiedRoles;
    }

    public List<User> getCreatedUsers() {
        return createdUsers;
    }

    public void setCreatedUsers(List<User> createdUsers) {
        this.createdUsers = createdUsers;
    }

    public List<User> getModifiedUsers() {
        return modifiedUsers;
    }

    public void setModifiedUsers(List<User> modifiedUsers) {
        this.modifiedUsers = modifiedUsers;
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
