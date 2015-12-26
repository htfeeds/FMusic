package com.htf.fmusic.services;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;

import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Song;

/**
 * @author HTFeeds
 */
public interface SongService extends BaseService<Song, Integer> {

    public List<Song> findByName(String name);

    public Song update(Song updated);

    @PreAuthorize("#song.createdByUser.username == authentication.name or hasRole('ADMIN') AND hasRole('DBA')")
    public Song delete(@P("song") Song song);

    public Artist addArtist(Integer id, Artist artist);

    public boolean removeArtist(Integer id, Artist artist);

}
