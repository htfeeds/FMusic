package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.exceptions.RoleNotFoundException;
import com.htf.fmusic.models.Role;
import com.htf.fmusic.repositories.RoleRepository;

/**
 * @author HTFeeds
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository repository;

    @Autowired
    RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        LOGGER.info("Finding all role entries.");

        List<Role> roleEntries = repository.findAll();
        LOGGER.info("Found {} role entries", roleEntries.size());

        return roleEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Integer id) {
        LOGGER.info("Finding role entry by using id: {}", id);

        Role roleEntry = findRoleEntryById(id);
        LOGGER.info("Found role entry: {}", roleEntry);

        return roleEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByType(String type) {
        LOGGER.info("Finding role entry by using type: {}", type);

        Role roleEntry = repository.findByType(type);
        LOGGER.info("Found role entry: {}", roleEntry);

        return roleEntry;
    }

    @Override
    @Transactional
    public Role create(Role newEntry) {
        LOGGER.info("Creating a new role entry by using information: {}", newEntry);

        Role created = repository.save(newEntry);
        LOGGER.info("Created a new role entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a role entry with id: {}", id);

        Role deleted = findRoleEntryById(id);
        LOGGER.debug("Found role entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Role update(Role updatedEntry) {
        LOGGER.info("Updating the information of a role entry by using information: {}", updatedEntry);

        Role updated = findRoleEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getType(), updatedEntry.getDescription());

        repository.flush();

        LOGGER.info("Updated the information of the role entry: {}", updated);
        return updated;
    }

    @Override
    public boolean isTypeUnique(Integer id, String type) {
        return (repository.findByType(type) == null || ((id != null) && (repository.findByType(type).getId() == id)));
    }

    private Role findRoleEntryById(Integer id) {
        Optional<Role> roleResult = repository.findOne(id);
        return roleResult.orElseThrow(() -> new RoleNotFoundException(id));
    }

}
