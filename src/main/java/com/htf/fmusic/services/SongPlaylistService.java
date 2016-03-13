package com.htf.fmusic.services;

import java.util.List;

import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.SongPlaylist;

/**
 * @author HTFeeds
 */
public interface SongPlaylistService extends BaseService<SongPlaylist, Integer> {

    public SongPlaylist update(SongPlaylist updated);

    public List<SongPlaylist> create(List<SongPlaylist> entities);

    public List<SongPlaylist> findByPlaylist(Playlist playlist);
    
    public List<SongPlaylist> findByPlaylistOrderByOrderAsc(Playlist playlist);

    public SongPlaylist changeOrder(Integer songPlaylistId, Integer order);

    public SongPlaylist remove(Integer id);

}
