package com.htf.fmusic.enums;

/**
 * @author HTFeeds
 */
public enum Country {
    UNKNOWN("Unknown"), VN("Viet Nam"), US("United States"), UK("United Kingdom"), FR("France"), KR("Korea"), CA("Canada");

    String country;

    private Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
