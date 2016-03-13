package com.htf.fmusic.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
/**
 * @author htf52
 *
 */
@Entity
@Table(name = "songs")
public final class Song extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID", unique = true, nullable = false)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "url", nullable = true)
    private String url;

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
    private String imageUrl;

    @JsonView(Views.Summary.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.Summary.class)
    @Column(name = "show_on_home")
    private Boolean onHome;

    @JsonView(Views.Summary.class)
    @Column(name = "is_published")
    private Boolean isPublished;

    @JsonView(Views.ExtendedPublic.class)
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonView(Views.ExtendedPublic.class)
    @ManyToOne
    @JoinColumn(name = "lyric_id")
    private Lyric lyric;

    @JsonView(Views.ExtendedPublic.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_artist_mappings", joinColumns = { @JoinColumn(name = "song_id") }, inverseJoinColumns = {
            @JoinColumn(name = "artist_id") })
    private Set<Artist> artists = new HashSet<Artist>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "song", cascade = CascadeType.ALL)
    private Set<SongPlaylist> songPlaylists = new HashSet<SongPlaylist>(0);

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOnHome() {
        return onHome;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public void setOnHome(Boolean onHome) {
        this.onHome = onHome;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<SongPlaylist> getSongPlaylists() {
        return songPlaylists;
    }

    public void setSongPlaylists(Set<SongPlaylist> songPlaylists) {
        this.songPlaylists = songPlaylists;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Lyric getLyric() {
        return lyric;
    }

    public void setLyric(Lyric lyric) {
        this.lyric = lyric;
    }

    public void update(String newName, String newUrl, Integer newTotalViews, Integer newWeekViews, String newCountry, String newDescription,
            Boolean newOnHome, Boolean newIsPublished, Genre newGenre) {
        this.name = newName;
        this.url = newUrl;
        this.totalViews = newTotalViews;
        this.weekViews = newWeekViews;
        this.country = newCountry;
        this.description = newDescription;
        this.onHome = newOnHome;
        this.isPublished = newIsPublished;
        this.genre = newGenre;
    }

    public Artist addArtist(Artist artist) {
        if (this.artists.contains(artist)) {
            return null;
        }
        this.artists.add(artist);
        return artist;
    }

    public boolean removeArtist(Artist artist) {
        return this.artists.remove(artist);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Song)) {
            return false;
        }
        Song other = (Song) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("totalViews", this.totalViews).append("url", this.url)
                .toString();
    }
}
