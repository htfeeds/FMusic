package com.htf.fmusic.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author HTFeeds
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    public List<T> findAll();

    public Optional<T> findOne(ID id);

    public T save(T persisted);

    public void delete(ID id);

    public long count();

}
