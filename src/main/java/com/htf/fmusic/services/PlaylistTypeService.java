package com.htf.fmusic.services;

import com.htf.fmusic.models.PlaylistType;

/**
 * @author HTFeeds
 */
public interface PlaylistTypeService extends BaseService<PlaylistType, Integer> {

    public PlaylistType update(PlaylistType updated);

    public boolean isPlaylistTypeUnique(Integer id, String name);

}
