package com.htf.fmusic.repositories;

import com.htf.fmusic.models.Role;

/**
 * @author HTFeeds
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

    public Role findByType(String roleType);
    
    public void flush();

}
