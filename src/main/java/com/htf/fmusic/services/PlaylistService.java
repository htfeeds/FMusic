package com.htf.fmusic.services;

import java.util.List;

import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Song;

/**
 * @author HTFeeds
 */
public interface PlaylistService extends BaseService<Playlist, Integer> {

    public Playlist update(Playlist updated);

    public Playlist addSongs(Integer id, List<Song> songs);

}
