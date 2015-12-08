package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.exceptions.ArtistNotFoundException;
import com.htf.fmusic.models.Artist;
import com.htf.fmusic.repositories.ArtistRepository;

/**
 * @author HTFeeds
 */
@Service
public class ArtistServiceImpl implements ArtistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRepository repository;

    @Autowired
    ArtistServiceImpl(ArtistRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        LOGGER.info("Finding all artist entries.");

        List<Artist> artistEntries = repository.findAll();
        LOGGER.info("Found {} artist entries", artistEntries.size());

        return artistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Artist findById(Integer id) {
        LOGGER.info("Finding artist entry by using id: {}", id);

        Artist artistEntry = findArtistEntryById(id);
        LOGGER.info("Found artist entry: {}", artistEntry);

        return artistEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Artist> findByName(String name) {
        LOGGER.info("Finding artist entry by using name: {}", name);

        List<Artist> artistEntries = repository.findByNameContainsIgnoreCase(name);
        LOGGER.info("Found artist entry: {}", artistEntries);

        return artistEntries;
    }

    @Override
    @Transactional
    public Artist create(Artist newEntry) {
        LOGGER.info("Creating a new artist entry by using information: {}", newEntry);

        Artist created = repository.save(newEntry);
        LOGGER.info("Created a new artist entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a artist entry with id: {}", id);

        Artist deleted = findArtistEntryById(id);
        LOGGER.debug("Found artist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Artist update(Artist updatedEntry) {
        LOGGER.info("Updating the information of a artist entry by using information: {}", updatedEntry);

        Artist updated = findArtistEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getName(), updatedEntry.getRealName(), updatedEntry.getBirthDate(), updatedEntry.getSex(),
                updatedEntry.getCountry(), updatedEntry.getCareer());
        repository.flush();

        LOGGER.info("Updated the information of the artist entry: {}", updated);
        return updated;
    }

    @Override
    public boolean isArtistNameUnique(Integer id, String name) {
        return (repository.findByName(name) == null || ((id != null) && (repository.findByName(name).getId() == id)));
    }

    private Artist findArtistEntryById(Integer id) {
        Optional<Artist> artistResult = repository.findOne(id);
        return artistResult.orElseThrow(() -> new ArtistNotFoundException(id));
    }

}
