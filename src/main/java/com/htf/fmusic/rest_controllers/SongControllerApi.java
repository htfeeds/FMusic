package com.htf.fmusic.rest_controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.models.Views;
import com.htf.fmusic.services.SongService;

/**
 * @author HTFeeds
 */
@RestController
@RequestMapping("/rest/song")
public class SongControllerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongControllerApi.class);

    private final SongService songService;

    @Autowired
    public SongControllerApi(SongService songService) {
        LOGGER.info("Inside constructor of SongControllerApi");
        this.songService = songService;
    }

    @JsonView(Views.ExtendedPublic.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Song findById(@PathVariable("id") Integer id) {
        Song song = songService.findById(id);
        return song;
    }

    @JsonView(Views.ExtendedPublic.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Song> findAll() {
        List<Song> songs = songService.findAll();
        return songs;
    }
}
