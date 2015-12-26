package com.htf.fmusic.repositories;

import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.models.SongPlaylistId;

/**
 * @author HTFeeds
 */
public interface SongPlaylistRepository extends BaseRepository<SongPlaylist, SongPlaylistId> {
    public Iterable<SongPlaylist> save(Iterable<SongPlaylist> entities);
}
