package com.htf.fmusic.services;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;

import com.htf.fmusic.models.Song;

/**
 * @author HTFeeds
 */
public interface SongService {

	public Song create(Song newSong);

	@PreAuthorize("#song.user.username == authentication.name or hasRole('ADMIN') AND hasRole('DBA')")
	public Song delete(@P("song") Song song);

	public List<Song> findAll();

	public Song findById(Integer id);
}