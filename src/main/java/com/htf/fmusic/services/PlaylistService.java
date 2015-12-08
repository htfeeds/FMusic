package com.htf.fmusic.services;

import com.htf.fmusic.models.Playlist;

/**
 * @author HTFeeds
 */
public interface PlaylistService extends BaseService<Playlist, Integer> {

    public Playlist update(Playlist updated);

}
