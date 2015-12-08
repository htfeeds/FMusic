package com.htf.fmusic.services;

import com.htf.fmusic.models.Lyric;

/**
 * @author HTFeeds
 */
public interface LyricService extends BaseService<Lyric, Integer> {

    public Lyric update(Lyric updated);

}
