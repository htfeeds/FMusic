package com.htf.fmusic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.htf.fmusic.models.Artist;
import com.htf.fmusic.services.ArtistService;

/**
 * @author HTFeeds
 */
@Component
public class ArtistConverter implements Converter<Object, Artist> {
    @Autowired
    ArtistService artistService;

    @Override
    public Artist convert(Object element) {
        if (element.equals("")) {
            return null;
        }

        if (element instanceof Artist) {
            return (Artist) element;
        }

        Integer id = Integer.parseInt((String) element);
        Artist artist = artistService.findById(id);
        return artist;
    }
}
