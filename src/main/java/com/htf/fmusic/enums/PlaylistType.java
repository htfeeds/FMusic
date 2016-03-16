package com.htf.fmusic.enums;

/**
 * @author HTFeeds
 */
public enum PlaylistType {
    OFFICIAL("Official"), USER("User"), COLLECTION("Collection"), TOP("Top"), HOT("Hot");

    String playlistType;

    private PlaylistType(String playlistType) {
        this.playlistType = playlistType;
    }

    public String getPlaylistType() {
        return playlistType;
    }
}
