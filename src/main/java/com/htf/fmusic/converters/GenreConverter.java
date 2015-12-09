package com.htf.fmusic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.htf.fmusic.models.Genre;
import com.htf.fmusic.services.GenreService;

/**
 * @author HTFeeds
 */
@Component
public class GenreConverter implements Converter<Object, Genre> {
    @Autowired
    GenreService genreService;

    @Override
    public Genre convert(Object element) {
        if (element.equals("")) {
            return null;
        }
        
        if (element instanceof Genre) {
            return (Genre) element;
        }

        Integer id = Integer.parseInt((String) element);
        Genre genre = genreService.findById(id);
        return genre;
    }
}
