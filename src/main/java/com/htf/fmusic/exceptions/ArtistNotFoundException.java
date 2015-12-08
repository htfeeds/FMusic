package com.htf.fmusic.exceptions;

/**
 * @author HTFeeds
 */
public class ArtistNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Integer id;

	public ArtistNotFoundException(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}