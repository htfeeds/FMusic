package com.htf.fmusic.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "artists")
public final class Artist extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "real_name")
    private String realName;

    @JsonView(Views.Summary.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_dt")
    private Date birthDate;

    @JsonView(Views.Summary.class)
    @Column(name = "sex")
    private String sex;

    @JsonView(Views.Summary.class)
    @Column(name = "country")
    private String country;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "image_url")
    private String imageUrl;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "career")
    private String career;

    @ManyToMany(mappedBy = "artists")
    private Set<Song> songs = new HashSet<Song>();

    @OneToMany(mappedBy = "artist")
    private List<Playlist> playlists = new ArrayList<Playlist>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Song> getUsers() {
        return songs;
    }

    public void setUsers(Set<Song> users) {
        this.songs = users;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void update(String newName, String newRealName, Date newBirthDate, String newSex, String newCountry, String newCareer) {
        this.name = newName;
        this.realName = newRealName;
        this.birthDate = newBirthDate;
        this.sex = newSex;
        this.country = newCountry;
        this.career = newCareer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("imageUrl", this.imageUrl).toString();
    }

}
