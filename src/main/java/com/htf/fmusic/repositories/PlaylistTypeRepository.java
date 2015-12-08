package com.htf.fmusic.repositories;

import com.htf.fmusic.models.PlaylistType;

/**
 * @author HTFeeds
 */
public interface PlaylistTypeRepository extends BaseRepository<PlaylistType, Integer> {
    
    public PlaylistType findByName(String name);
    
}
