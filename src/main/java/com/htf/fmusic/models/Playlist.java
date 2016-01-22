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
    private Integer totalViews;

    @JsonView(Views.Summary.class)
    @Column(name = "image_url")
    private String imageUrl;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "playlist_type_id")
    private PlaylistType playlistType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist")
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

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
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

    public PlaylistType getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
    }

    public Set<SongPlaylist> getSongPlaylists() {
        return songPlaylists;
    }

    public void setSongPlaylists(Set<SongPlaylist> songPlaylists) {
        this.songPlaylists = songPlaylists;
    }

    public void update(String newName, Integer newTotalViews, Artist newArtist, Genre newGenre, PlaylistType newPlaylistType) {
        this.name = newName;
        this.totalViews = newTotalViews;
        this.artist = newArtist;
        this.genre = newGenre;
        this.playlistType = newPlaylistType;
    }

    public void addSongPlaylist(SongPlaylist songPlaylists) {
        this.songPlaylists.add(songPlaylists);
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
