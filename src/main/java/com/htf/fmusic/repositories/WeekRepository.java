package com.htf.fmusic.repositories;

import com.htf.fmusic.models.Week;

/**
 * @author HTFeeds
 */
public interface WeekRepository extends BaseRepository<Week, Integer> {
    public Week findFirstByOrderByEndDateDesc();
}
