package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.htf.fmusic.exceptions.SongNotFoundException;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.repositories.SongRepository;

/**
 * @author HTFeeds
 */
@Service
public class SongServiceImpl implements SongService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

    private final SongRepository repository;

    @Autowired
    SongServiceImpl(SongRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Song> findAll() {
        LOGGER.info("Finding all song entries.");

        List<Song> songEntries = repository.findAll();
        LOGGER.info("Found {} song entries", songEntries.size());

        return songEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Song findById(Integer id) {
        LOGGER.info("Finding song entry by using id: {}", id);

        Song songEntry = findSongEntryById(id);
        if (songEntry != null) {
            Hibernate.initialize(songEntry.getArtists());
        }

        LOGGER.info("Found song entry: {}", songEntry);

        return songEntry;
    }

    @Override
    @Transactional
    public Song create(Song newEntry) {
        LOGGER.info("Creating a new song entry by using information: {}", newEntry);

        Song created = repository.save(newEntry);
        LOGGER.info("Created a new song entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a song entry with id: {}", id);

        Song deleted = findSongEntryById(id);
        LOGGER.debug("Found song entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted song entry: {}", deleted);
    }

    @Override
    @Transactional
    public Song update(Song updatedEntry) {
        LOGGER.info("Updating the information of a song entry by using information: {}", updatedEntry);

        Song updated = findSongEntryById(updatedEntry.getId());
        //

        //We need to flush the changes or otherwise the returned object
        //doesn't contain the updated audit information.
        repository.flush();

        LOGGER.info("Updated the information of the song entry: {}", updated);
        return updated;
    }

    private Song findSongEntryById(Integer id) {
        Optional<Song> songResult = repository.findOne(id);
        return songResult.orElseThrow(() -> new SongNotFoundException(id));
    }

    @Override
    public List<Song> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Song delete(Song song) {
        // TODO Auto-generated method stub
        return null;
    }

}
