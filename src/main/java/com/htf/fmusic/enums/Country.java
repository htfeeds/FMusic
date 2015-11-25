package com.htf.fmusic.enums;

/**
 * @author HTFeeds
 */
public enum Country {
	VN("Viet Nam"), USA("United States"), UK("United Kingdom"), FR("France");

	String country;

	private Country(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}
}