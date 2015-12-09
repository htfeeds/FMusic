package com.htf.fmusic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.htf.fmusic.models.PlaylistType;
import com.htf.fmusic.services.PlaylistTypeService;

/**
 * @author HTFeeds
 */
@Component
public class PlaylistTypeConverter implements Converter<Object, PlaylistType> {
    @Autowired
    PlaylistTypeService playlistTypeService;

    @Override
    public PlaylistType convert(Object element) {
        if (element.equals("")) {
            return null;
        }

        if (element instanceof PlaylistType) {
            return (PlaylistType) element;
        }

        Integer id = Integer.parseInt((String) element);
        PlaylistType playlistType = playlistTypeService.findById(id);
        return playlistType;
    }
}
