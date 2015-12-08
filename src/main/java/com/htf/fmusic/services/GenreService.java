package com.htf.fmusic.services;

import com.htf.fmusic.models.Genre;

/**
 * @author HTFeeds
 */
public interface GenreService extends BaseService<Genre, Integer> {

    public Genre update(Genre updated);

    public boolean isGenreNameUnique(Integer id, String name);

}
