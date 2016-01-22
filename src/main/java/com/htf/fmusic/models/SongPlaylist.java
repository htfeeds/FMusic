package com.htf.fmusic.models;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "song_playlist_mappings")
@AssociationOverrides({ @AssociationOverride(name = "song", joinColumns = @JoinColumn(name = "SONG_ID") ),
        @AssociationOverride(name = "playlist", joinColumns = @JoinColumn(name = "PLAYLIST_ID") ) })
public class SongPlaylist implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @ManyToOne
    private Song song;

    @JsonView(Views.Summary.class)
    @ManyToOne
    private Playlist playlist;

    @JsonView(Views.Summary.class)
    @Column(name = "order_index")
    private Integer order;

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

    public void updateOrder(Integer order) {
        this.order = order;
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
