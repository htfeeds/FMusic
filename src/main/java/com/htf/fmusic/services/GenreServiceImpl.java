package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.exceptions.GenreNotFoundException;
import com.htf.fmusic.models.Genre;
import com.htf.fmusic.repositories.GenreRepository;

/**
 * @author HTFeeds
 */
@Service
public class GenreServiceImpl implements GenreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository repository;

    @Autowired
    GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        LOGGER.info("Finding all genre entries.");

        List<Genre> genreEntries = repository.findAll();
        LOGGER.info("Found {} genre entries", genreEntries.size());

        return genreEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Integer id) {
        LOGGER.info("Finding genre entry by using id: {}", id);

        Genre genreEntry = findGenreEntryById(id);
        LOGGER.info("Found genre entry: {}", genreEntry);

        return genreEntry;
    }

    @Override
    @Transactional
    public Genre create(Genre newEntry) {
        LOGGER.info("Creating a new genre entry by using information: {}", newEntry);

        Genre created = repository.save(newEntry);
        LOGGER.info("Created a new genre entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a genre entry with id: {}", id);

        Genre deleted = findGenreEntryById(id);
        LOGGER.debug("Found genre entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Genre update(Genre updatedEntry) {
        LOGGER.info("Updating the information of a genre entry by using information: {}", updatedEntry);

        Genre updated = findGenreEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getName(), updatedEntry.getDescription());
        repository.flush();

        LOGGER.info("Updated the information of the genre entry: {}", updated);
        return updated;
    }

    @Override
    public boolean isGenreNameUnique(Integer id, String name) {
        return (repository.findByName(name) == null || ((id != null) && (repository.findByName(name).getId() == id)));
    }

    private Genre findGenreEntryById(Integer id) {
        Optional<Genre> genreResult = repository.findOne(id);
        return genreResult.orElseThrow(() -> new GenreNotFoundException(id));
    }

}
