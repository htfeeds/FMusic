package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.exceptions.PlaylistNotFoundException;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.repositories.PlaylistRepository;

/**
 * @author HTFeeds
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistServiceImpl.class);

    private final PlaylistRepository repository;

    @Autowired
    PlaylistServiceImpl(PlaylistRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> findAll() {
        LOGGER.info("Finding all playlist entries.");

        List<Playlist> playlistEntries = repository.findAll();
        LOGGER.info("Found {} playlist entries", playlistEntries.size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist findById(Integer id) {
        LOGGER.info("Finding playlist entry by using id: {}", id);

        Playlist playlistEntry = findPlaylistEntryById(id);
        LOGGER.info("Found playlist entry: {}", playlistEntry);

        return playlistEntry;
    }

    @Override
    @Transactional
    public Playlist create(Playlist newEntry) {
        LOGGER.info("Creating a new playlist entry by using information: {}", newEntry);

        Playlist created = repository.save(newEntry);
        LOGGER.info("Created a new playlist entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a playlist entry with id: {}", id);

        Playlist deleted = findPlaylistEntryById(id);
        LOGGER.debug("Found playlist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Playlist update(Playlist updatedEntry) {
        LOGGER.info("Updating the information of a playlist entry by using information: {}", updatedEntry);

        Playlist updated = findPlaylistEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getName(), updatedEntry.getTotalViews(), updatedEntry.getArtist(), updatedEntry.getGenre(),
                updatedEntry.getPlaylistType());
        repository.flush();

        LOGGER.info("Updated the information of the playlist entry: {}", updated);
        return updated;
    }

    private Playlist findPlaylistEntryById(Integer id) {
        Optional<Playlist> playlistResult = repository.findOne(id);
        return playlistResult.orElseThrow(() -> new PlaylistNotFoundException(id));
    }

}
