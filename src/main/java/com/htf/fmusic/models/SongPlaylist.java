package com.htf.fmusic.models;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "song_playlist_mappings")
@AssociationOverrides({ @AssociationOverride(name = "pk.song", joinColumns = @JoinColumn(name = "SONG_ID") ),
        @AssociationOverride(name = "pk.playlist", joinColumns = @JoinColumn(name = "PLAYLIST_ID") ) })
public class SongPlaylist implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    private SongPlaylistId pk = new SongPlaylistId();

    @JsonView(Views.Summary.class)
    private Integer order;

    @EmbeddedId
    public SongPlaylistId getPk() {
        return pk;
    }

    public void setPk(SongPlaylistId pk) {
        this.pk = pk;
    }

    @Column(name = "order_index")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Transient
    public Song getSong() {
        return getPk().getSong();
    }

    public void setSong(Song song) {
        getPk().setSong(song);
    }

    @Transient
    public Playlist getPlaylist() {
        return getPk().getPlaylist();
    }

    public void setPlaylist(Playlist playlist) {
        getPk().setPlaylist(playlist);
    }

}
