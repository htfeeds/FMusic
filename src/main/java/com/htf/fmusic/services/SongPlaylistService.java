package com.htf.fmusic.services;

import com.htf.fmusic.models.SongPlaylist;

/**
 * @author HTFeeds
 */
public interface SongPlaylistService extends BaseService<SongPlaylist, Integer> {

    public SongPlaylist update(SongPlaylist updated);

}
