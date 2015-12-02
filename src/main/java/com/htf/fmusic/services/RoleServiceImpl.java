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
    @Transactional
    public Role create(Role newRole) {
        LOGGER.info("Creating a new role entry by using information: {}", newRole);

        Role created = repository.save(newRole);
        LOGGER.info("Created a new role entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a role entry with id: {}", id);
        
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

    private Role findRoleEntryById(Integer id) {
        Optional<Role> roleResult = repository.findOne(id);
        return roleResult.orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public Role findByType(String roleType) {
        LOGGER.info("Finding role entry by using type: {}", roleType);

        Role roleEntry = repository.findByType(roleType);
        LOGGER.info("Found role entry: {}", roleEntry);

        return roleEntry;
    }
}
