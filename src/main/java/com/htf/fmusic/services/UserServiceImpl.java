package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.htf.fmusic.exceptions.UserNotFoundException;
import com.htf.fmusic.models.User;
import com.htf.fmusic.repositories.UserRepository;

/**
 * @author HTFeeds
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User create(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        LOGGER.info("Creating a new user entry by using information: {}", newUser);

        User created = repository.save(newUser);
        LOGGER.info("Created a new user entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a user entry with id: {}", id);
        repository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        LOGGER.info("Finding all user entries.");

        List<User> userEntries = repository.findAll();
        LOGGER.info("Found {} user entries", userEntries.size());

        return userEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        LOGGER.info("Finding user entry by using id: {}", id);

        User userEntry = findUserEntryById(id);
        if (userEntry != null) {
            Hibernate.initialize(userEntry.getRoles());
        }

        LOGGER.info("Found user entry: {}", userEntry);

        return userEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        LOGGER.info("Finding user entry by using username: {}", username);

        User userEntry = repository.findByUsername(username);
        if (userEntry != null) {
            Hibernate.initialize(userEntry.getRoles());
        }
        
        LOGGER.info("Found user entry: {}", userEntry);

        return userEntry;
    }

    @Override
    public User update(User user) {
        LOGGER.info("Updating the information of a user entry by using information: {}", user);

        User updated = findUserEntryById(user.getId());
        updated.update(user.getFullname(), user.getEmail(), user.getBirthDate(), user.getPhoneNumber(), user.getSex(), user.getState(),
                user.getRoles());
        repository.flush();

        LOGGER.info("Updated the information of the user entry: {}", updated);
        return updated;
    }

    @Override
    public User updateAvatar(Integer id, String newImageUrl) {
        User updated = findUserEntryById(id);

        return updated;
    }

    @Override
    public User updatePassword(Integer id, String newPassword) {
        User updated = findUserEntryById(id);
        updated.setPassword(passwordEncoder.encode(newPassword));
        repository.flush();

        return updated;
    }

    @Override
    public boolean isUsernameUnique(Integer id, String username) {
        return (repository.findByUsername(username) == null || ((id != null) && (repository.findByUsername(username).getId() == id)));
    }

    @Override
    public boolean isEmailUnique(Integer id, String email) {
        return (repository.findByEmail(email) == null || ((id != null) && (repository.findByEmail(email).getId() == id)));
    }

    private User findUserEntryById(Integer id) {
        Optional<User> userResult = repository.findOne(id);
        return userResult.orElseThrow(() -> new UserNotFoundException(id));
    }
}
