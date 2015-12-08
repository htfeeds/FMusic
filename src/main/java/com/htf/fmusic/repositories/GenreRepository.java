package com.htf.fmusic.repositories;

import com.htf.fmusic.models.Genre;

/**
 * @author HTFeeds
 */
public interface GenreRepository extends BaseRepository<Genre, Integer> {
    
    public Genre findByName(String name);
    
}
