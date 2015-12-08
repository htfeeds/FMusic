package com.htf.fmusic.exceptions;

/**
 * @author HTFeeds
 */
public class PlaylistTypeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Integer id;

	public PlaylistTypeNotFoundException(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}