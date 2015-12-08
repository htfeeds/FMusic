package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.exceptions.PlaylistTypeNotFoundException;
import com.htf.fmusic.models.PlaylistType;
import com.htf.fmusic.repositories.PlaylistTypeRepository;

/**
 * @author HTFeeds
 */
@Service
public class PlaylistTypeServiceImpl implements PlaylistTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistTypeServiceImpl.class);

    private final PlaylistTypeRepository repository;

    @Autowired
    PlaylistTypeServiceImpl(PlaylistTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaylistType> findAll() {
        LOGGER.info("Finding all playlistType entries.");

        List<PlaylistType> playlistTypeEntries = repository.findAll();
        LOGGER.info("Found {} playlistType entries", playlistTypeEntries.size());

        return playlistTypeEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public PlaylistType findById(Integer id) {
        LOGGER.info("Finding playlistType entry by using id: {}", id);

        PlaylistType playlistTypeEntry = findPlaylistTypeEntryById(id);
        LOGGER.info("Found playlistType entry: {}", playlistTypeEntry);

        return playlistTypeEntry;
    }

    @Override
    @Transactional
    public PlaylistType create(PlaylistType newEntry) {
        LOGGER.info("Creating a new playlistType entry by using information: {}", newEntry);

        PlaylistType created = repository.save(newEntry);
        LOGGER.info("Created a new playlistType entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a playlistType entry with id: {}", id);

        PlaylistType deleted = findPlaylistTypeEntryById(id);
        LOGGER.debug("Found playlistType entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public PlaylistType update(PlaylistType updatedEntry) {
        LOGGER.info("Updating the information of a playlistType entry by using information: {}", updatedEntry);

        PlaylistType updated = findPlaylistTypeEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getName(), updatedEntry.getDescription());
        repository.flush();

        LOGGER.info("Updated the information of the playlistType entry: {}", updated);
        return updated;
    }

    @Override
    public boolean isPlaylistTypeUnique(Integer id, String name) {
        return (repository.findByName(name) == null || ((id != null) && (repository.findByName(name).getId() == id)));
    }

    private PlaylistType findPlaylistTypeEntryById(Integer id) {
        Optional<PlaylistType> playlistTypeResult = repository.findOne(id);
        return playlistTypeResult.orElseThrow(() -> new PlaylistTypeNotFoundException(id));
    }

}
