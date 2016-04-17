package com.example.chens.PlaymySong.entities;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public abstract class SongInfo {
    private String title;
    private String artist;
    private String album;
    private String lyric;
    private Comments comments;
    private int rawSourceID;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public int getRawSourceID() {
        return rawSourceID;
    }

    public void setRawSourceID(int rawSourceID) {
        this.rawSourceID = rawSourceID;
    }
}
