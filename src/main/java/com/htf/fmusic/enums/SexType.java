package com.htf.fmusic.enums;

/**
 * @author HTFeeds
 */
public enum SexType {
	MALE("Male"), FEMALE("Female");

	String sexType;

	private SexType(String sexType) {
		this.sexType = sexType;
	}

	public String getSexType() {
		return sexType;
	}
}