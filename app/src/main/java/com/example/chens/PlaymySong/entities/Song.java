package com.example.chens.PlaymySong.entities;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public class Song extends SongInfo implements updateMySong {
    public Song(String title, String artist) {
        setTitle(title);
        setArtist(artist);
    }
    public Song(String title, String artist, String album, int rawSourceID) {
        setTitle(title);
        setArtist(artist);
        setAlbum(album);
        setRawSourceID(rawSourceID);
    }
}
