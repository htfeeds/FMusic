package com.htf.fmusic.services;

import java.util.List;

import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.models.SongPlaylistId;

/**
 * @author HTFeeds
 */
public interface SongPlaylistService extends BaseService<SongPlaylist, SongPlaylistId> {

    public SongPlaylist update(SongPlaylist updated);

    public List<SongPlaylist> create(List<SongPlaylist> entities);

}
