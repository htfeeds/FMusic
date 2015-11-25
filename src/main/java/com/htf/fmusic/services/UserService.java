package com.htf.fmusic.services;

import com.htf.fmusic.models.User;

/**
 * @author HTFeeds
 */
public interface UserService extends BaseService<User, Integer> {

    public User update(User user);

    public User updateAvatar(Integer id, String newImageUrl);

    public User updatePassword(Integer id, String newPassword);

    public User findByUsername(String username);

    public boolean isUsernameUnique(Integer id, String username);

    public boolean isEmailUnique(Integer id, String email);

}
