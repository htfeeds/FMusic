package com.htf.fmusic.repositories;

import java.util.List;

import com.htf.fmusic.models.Playlist;

/**
 * @author HTFeeds
 */
public interface PlaylistRepository extends BaseRepository<Playlist, Integer> {

    public List<Playlist> findByNameContainsIgnoreCase(String name);

}
