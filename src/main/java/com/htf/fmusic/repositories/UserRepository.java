package com.htf.fmusic.repositories;

import com.htf.fmusic.models.User;

/**
 * @author HTFeeds
 */
public interface UserRepository extends BaseRepository<User, Integer> {

    public User findByUsername(String username);

    public User findByEmail(String email);
    
    public void flush();

}
