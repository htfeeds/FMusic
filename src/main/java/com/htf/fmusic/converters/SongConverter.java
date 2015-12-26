package com.htf.fmusic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.htf.fmusic.models.Song;
import com.htf.fmusic.services.SongService;

/**
 * @author HTFeeds
 */
@Component
public class SongConverter implements Converter<Object, Song> {
    @Autowired
    SongService songService;

    @Override
    public Song convert(Object element) {
        if (element.equals("")) {
            return null;
        }

        if (element instanceof Song) {
            return (Song) element;
        }

        Integer id = Integer.parseInt((String) element);
        Song song = songService.findById(id);
        return song;
    }
}
