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

/**
 * @author HTFeeds
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
    private Integer totalViews;

    @JsonView(Views.Summary.class)
    @Column(name = "week_views")
    private Integer weekViews;

    @JsonView(Views.Summary.class)
    @Column(name = "description")
    private String description;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.song", cascade = CascadeType.ALL)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void update(String newName, String newUrl, Integer newTotalViews, Integer newWeekViews, String newDescription, Genre newGenre) {
        this.name = newName;
        this.url = newUrl;
        this.totalViews = newTotalViews;
        this.weekViews = newWeekViews;
        this.description = newDescription;
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
