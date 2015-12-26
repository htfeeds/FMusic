package com.htf.fmusic.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author HTFeeds
 */
@Embeddable
public class SongPlaylistId implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    private Song song;
    
    @JsonView(Views.Summary.class)
    private Playlist playlist;

    @ManyToOne
    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @ManyToOne
    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
