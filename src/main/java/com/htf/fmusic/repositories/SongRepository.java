package com.htf.fmusic.repositories;

import java.util.List;

import com.htf.fmusic.models.Song;

/**
 * @author HTFeeds
 */
public interface SongRepository extends BaseRepository<Song, Integer> {
    
    public List<Song> findByNameContainsIgnoreCase(String name);
    
}
