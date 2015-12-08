package com.htf.fmusic.services;

import java.util.List;

import com.htf.fmusic.models.Artist;

/**
 * @author HTFeeds
 */
public interface ArtistService extends BaseService<Artist, Integer> {

    public List<Artist> findByName(String name);

    public Artist update(Artist updated);

    public boolean isArtistNameUnique(Integer id, String name);

}
