package com.htf.fmusic.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.htf.fmusic.models.Song;

/**
 * @author HTFeeds
 */
public interface SongRepository extends BaseRepository<Song, Integer> {

    public Page<Song> findByIsPublished(Boolean isPublished, Pageable pageable);

    public Page<Song> findByIsPublishedAndGenreName(Boolean isPublished, String genreName, Pageable pageable);

    public List<Song> findByNameContainsIgnoreCase(String name);

    public List<Song> findTop20ByOnHomeOrderByIdDesc(Boolean onHome);

    public List<Song> findTop6ByIsPublishedTrueAndArtistsNameOrderByIdDesc(String name);

    public List<Song> findByIsPublishedFalseAndType(String type);

}
