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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "songs")
public final class Song extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = true)
    private String url;

    @Column(name = "total_views")
    private Integer totalViews;

    @Column(name = "week_views")
    private Integer weekViews;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_artist_mappings", joinColumns = { @JoinColumn(name = "song_id") }, inverseJoinColumns = {
            @JoinColumn(name = "artist_id") })
    private Set<Artist> artists = new HashSet<Artist>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_playlist_mappings", joinColumns = { @JoinColumn(name = "song_id") }, inverseJoinColumns = {
            @JoinColumn(name = "playlist_id") })
    private Set<Artist> playlists = new HashSet<Artist>();

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
    
    @ManyToOne
    @JoinColumn(name = "lyric_id")
    private Lyric lyric;
    
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

    public Set<Artist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Artist> playlists) {
        this.playlists = playlists;
    }

    public void update(String newName, String newUrl, Integer newTotalViews, Integer newWeekViews, String newDescription, Set<Artist> newArtists) {

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
