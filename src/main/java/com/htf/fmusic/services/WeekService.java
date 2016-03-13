package com.htf.fmusic.services;

import com.htf.fmusic.models.Week;

/**
 * @author HTFeeds
 */
public interface WeekService extends BaseService<Week, Integer> {

    public Week update(Week updated);

}
