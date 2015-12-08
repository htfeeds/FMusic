package com.htf.fmusic.services;

import com.htf.fmusic.models.Role;

/**
 * @author HTFeeds
 */
public interface RoleService extends BaseService<Role, Integer> {

    public Role findByType(String type);

    public Role update(Role updated);

    public boolean isTypeUnique(Integer id, String type);

}
