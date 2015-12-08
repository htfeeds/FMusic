package com.htf.fmusic.exceptions;

/**
 * @author HTFeeds
 */
public class PlaylistNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Integer id;

	public PlaylistNotFoundException(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}