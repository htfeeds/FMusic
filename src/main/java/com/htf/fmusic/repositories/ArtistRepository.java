package com.htf.fmusic.repositories;

import java.util.List;

import com.htf.fmusic.models.Artist;

/**
 * @author HTFeeds
 */
public interface ArtistRepository extends BaseRepository<Artist, Integer> {

    public List<Artist> findByNameContainsIgnoreCase(String name);

    public Artist findByName(String name);

}
