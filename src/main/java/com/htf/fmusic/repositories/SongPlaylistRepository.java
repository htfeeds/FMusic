package com.htf.fmusic.repositories;

import java.util.List;

import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.SongPlaylist;

/**
 * @author HTFeeds
 */
public interface SongPlaylistRepository extends BaseRepository<SongPlaylist, Integer> {
    
    public Iterable<SongPlaylist> save(Iterable<SongPlaylist> entities);

    public List<SongPlaylist> findByPlaylist(Playlist playlist);
    
}
