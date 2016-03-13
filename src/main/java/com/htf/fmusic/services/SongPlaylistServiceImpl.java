package com.htf.fmusic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.htf.fmusic.exceptions.SongPlaylistNotFoundException;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.repositories.SongPlaylistRepository;

/**
 * @author HTFeeds
 */
@Service
public class SongPlaylistServiceImpl implements SongPlaylistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongPlaylistServiceImpl.class);

    private final SongPlaylistRepository repository;

    @Autowired
    SongPlaylistServiceImpl(SongPlaylistRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findAll() {
        LOGGER.info("Finding all songPlaylist entries.");

        List<SongPlaylist> songPlaylistEntries = repository.findAll();
        LOGGER.info("Found {} songPlaylist entries", songPlaylistEntries.size());

        return songPlaylistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public SongPlaylist findById(Integer id) {
        LOGGER.info("Finding songPlaylist entry by using id: {}", id);

        SongPlaylist songPlaylistEntry = findSongPlaylistEntryById(id);
        LOGGER.info("Found songPlaylist entry: {}", songPlaylistEntry);

        return songPlaylistEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findByPlaylist(Playlist playlist) {
        LOGGER.info("Finding all songPlaylist entries by using playlist: {}", playlist);

        List<SongPlaylist> lsSongPlaylist = repository.findByPlaylist(playlist);
        LOGGER.info("Found songPlaylist entries: {}", lsSongPlaylist);

        for (SongPlaylist sp : lsSongPlaylist) {
            Hibernate.initialize(sp.getSong().getArtists());
        }

        return lsSongPlaylist;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findByPlaylistOrderByOrderAsc(Playlist playlist) {
        LOGGER.info("Finding all songPlaylist entries by using playlist: {}", playlist);

        List<SongPlaylist> lsSongPlaylist = repository.findByPlaylistOrderByOrderAsc(playlist);
        LOGGER.info("Found songPlaylist entries: {}", lsSongPlaylist);

        for (SongPlaylist sp : lsSongPlaylist) {
            Hibernate.initialize(sp.getSong().getArtists());
        }

        return lsSongPlaylist;
    }

    @Override
    @Transactional
    public SongPlaylist create(SongPlaylist newEntry) {
        LOGGER.info("Creating a new songPlaylist entry by using information: {}", newEntry);

        SongPlaylist created = repository.save(newEntry);
        LOGGER.info("Created a new songPlaylist entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public List<SongPlaylist> create(List<SongPlaylist> entities) {
        LOGGER.info("Creating new {} songPlaylist entries", entities.size());

        Iterable<SongPlaylist> created = repository.save(entities);
        ArrayList<SongPlaylist> songPlaylists = Lists.newArrayList(created);
        LOGGER.info("Created new {} songPlaylist entries", songPlaylists.size());

        return songPlaylists;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a songPlaylist entry with id: {}", id);

        SongPlaylist deleted = findSongPlaylistEntryById(id);
        LOGGER.debug("Found songPlaylist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public SongPlaylist remove(Integer id) {
        LOGGER.info("Deleting a songPlaylist entry with id: {}", id);

        SongPlaylist deleted = findSongPlaylistEntryById(id);
        LOGGER.debug("Found songPlaylist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");

        return deleted;
    }

    @Override
    @Transactional
    public SongPlaylist update(SongPlaylist updatedEntry) {
        LOGGER.info("Updating the information of a songPlaylist entry by using information: {}", updatedEntry);

        //
        repository.flush();

        //LOGGER.info("Updated the information of the songPlaylist entry: {}", updated);
        return null;
    }

    @Override
    @Transactional
    public SongPlaylist changeOrder(Integer songPlaylistId, Integer order) {
        LOGGER.info("Updating the order of a songPlaylist entry id: {}", songPlaylistId);

        SongPlaylist sp = findById(songPlaylistId);
        sp.updateOrder(order);
        repository.flush();

        LOGGER.info("Updated the order of the songPlaylist entry: {}", sp);
        return sp;
    }

    private SongPlaylist findSongPlaylistEntryById(Integer id) {
        Optional<SongPlaylist> songPlaylistResult = repository.findOne(id);
        return songPlaylistResult.orElseThrow(() -> new SongPlaylistNotFoundException(id));
    }

}
