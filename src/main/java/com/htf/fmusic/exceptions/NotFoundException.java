package com.htf.fmusic.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author HTFeeds
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7582550023114509895L;

    public NotFoundException(String message) {
        super(message);
    }
}
