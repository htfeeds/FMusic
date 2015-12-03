package com.htf.fmusic.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author HTFeeds
 */
public interface BaseService<T, ID extends Serializable> {

    public List<T> findAll();

    public T findById(ID id);

    public T create(T newEntry);

    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    public void delete(ID id);

}
