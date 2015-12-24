package com.htf.fmusic.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "song_playlist_mappings")
public class SongPlaylist extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @Column(name = "order")
    private Integer order;
    
    public SongPlaylist(Song song, Playlist playlist, Integer order) {
        super();
        this.song = song;
        this.playlist = playlist;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void update(Song newSong, Playlist newPlaylist, Integer newOrder) {
        this.song = newSong;
        this.playlist = newPlaylist;
        this.order = newOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SongPlaylist)) {
            return false;
        }
        SongPlaylist other = (SongPlaylist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("song", this.song).append("playlist", this.playlist).append("order", this.order)
                .toString();
    }
}
