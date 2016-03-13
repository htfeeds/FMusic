package com.htf.fmusic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.htf.fmusic.models.Week;
import com.htf.fmusic.services.WeekService;

/**
 * @author HTFeeds
 */
@Component
public class WeekConverter implements Converter<Object, Week> {
    @Autowired
    WeekService weekService;

    @Override
    public Week convert(Object element) {
        if (element.equals("")) {
            return null;
        }
        
        if (element instanceof Week) {
            return (Week) element;
        }

        Integer id = Integer.parseInt((String) element);
        Week week = weekService.findById(id);
        return week;
    }
}
