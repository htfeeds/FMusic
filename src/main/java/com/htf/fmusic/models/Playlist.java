package com.htf.fmusic.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;
import com.htf.fmusic.enums.Country;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "playlists")
public final class Playlist extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYLIST_ID", unique = true, nullable = false)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "total_views")
    private Integer totalViews = 0;

    @JsonView(Views.Summary.class)
    @Column(name = "week_views")
    private Integer weekViews = 0;

    @JsonView(Views.Summary.class)
    @Column(name = "country")
    private String country = Country.UNKNOWN.getCountry();

    @JsonView(Views.Summary.class)
    @Column(name = "image_url")
    private String imageUrl = "/static/img/playlist/img-plist-full.jpg";

    @JsonView(Views.Summary.class)
    @Column(name = "show_on_home")
    private Boolean onHome;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "week_id")
    private Week week;

    @JsonView(Views.Summary.class)
    @Column(name = "slide_image_url")
    private String slideImageUrl;

    @JsonView(Views.Summary.class)
    @Column(name = "slide_actived")
    private Boolean slideActived;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonView(Views.Summary.class)
    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist")
    private Set<SongPlaylist> songPlaylists = new HashSet<SongPlaylist>(0);

    @ManyToMany(mappedBy = "playlists")
    private Set<User> users = new HashSet<User>();

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

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public Integer getWeekViews() {
        return weekViews;
    }

    public void setWeekViews(Integer weekViews) {
        this.weekViews = weekViews;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getOnHome() {
        return onHome;
    }

    public void setOnHome(Boolean onHome) {
        this.onHome = onHome;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public String getSlideImageUrl() {
        return slideImageUrl;
    }

    public void setSlideImageUrl(String slideImageUrl) {
        this.slideImageUrl = slideImageUrl;
    }

    public Boolean getSlideActived() {
        return slideActived;
    }

    public void setSlideActived(Boolean slideActived) {
        this.slideActived = slideActived;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<SongPlaylist> getSongPlaylists() {
        return songPlaylists;
    }

    public void setSongPlaylists(Set<SongPlaylist> songPlaylists) {
        this.songPlaylists = songPlaylists;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void update(String newName, Integer newTotalViews, Integer newWeekViews, String newCountry, Artist newArtist, Genre newGenre,
            String newType, Boolean newOnHome, Week newWeek, Boolean newSlideActived) {
        this.name = newName;
        this.totalViews = newTotalViews;
        this.weekViews = newWeekViews;
        this.country = newCountry;
        this.artist = newArtist;
        this.genre = newGenre;
        this.type = newType;
        this.onHome = newOnHome;
        this.week = newWeek;
        this.slideActived = newSlideActived;
    }

    public void addSongPlaylist(SongPlaylist songPlaylists) {
        this.songPlaylists.add(songPlaylists);
    }

    public void incrementViews() {
        this.weekViews += 1;
        this.totalViews += 1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("totalViews", this.totalViews).toString();
    }
}
