package com.example.chens.PlaymySong.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chens.PlaymySong.entities.Song;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public class MyLocalDB {
    private DatabaseOpenHelper databaseOpenHelper;
    private final String favoriteListTableName = "FavoriteListTable";
    private final String playListTableName = "PlayListTable";
    private final String recentPlayListTableName = "RecentPlayListTable";
    private final String wishListTableName = "WishListTable";
    private final String titleAndArtist = "title_artist";
    private final String album = "album";
    private SQLiteDatabase database;

    public MyLocalDB(Context context) {
        String dbName = "myDatabase";
        databaseOpenHelper =
                new DatabaseOpenHelper(context, dbName, null, 1);
    }

    ///////////////////////// CRUD method begin /////////////////////////////
    public void addToFavoriteList(Song song) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndArtist, song.getTitle() + "+" + song.getArtist());
        newRecords.put(album, song.getAlbum());
        open();
        database.insert(favoriteListTableName, null, newRecords);
        close();
    }

    public void addToPlayList(Song song) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndArtist, song.getTitle() + "+" + song.getArtist());
        newRecords.put(album, song.getAlbum());
        open();
        database.insert(playListTableName, null, newRecords);
        close();
    }

    public void addToRecentPlayList(Song song) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndArtist, song.getTitle() + "+" + song.getArtist());
        newRecords.put(album, song.getAlbum());
        open();
        database.insert(recentPlayListTableName, null, newRecords);
        close();
    }

    public void addToWishList(Song song) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndArtist, song.getTitle() + "+" + song.getArtist());
        newRecords.put(album, song.getAlbum());
        open();
        database.insert(wishListTableName, null, newRecords);
        close();
    }

    public void deleteFromFavoriteList(String title, String singer) {
        open();
        database.delete(favoriteListTableName, titleAndArtist + "=" + title + "+" + singer, null);
        close();
    }

    public void deleteFromPlayList(String title, String singer) {
        open();
        database.delete(playListTableName, titleAndArtist + "=" + title + "+" + singer, null);
        close();
    }

    public void deleteFromRecentPlayList(String title, String singer) {
        open();
        database.delete(recentPlayListTableName, titleAndArtist + "=" + title + "+" + singer, null);
        close();
    }

    public void deleteFromWishList(String title, String singer) {
        open();
        database.delete(wishListTableName, titleAndArtist + "=" + title + "+" + singer, null);
        close();
    }

    public Cursor getFavoriteListAll() {
        return database.query(favoriteListTableName, new String[]{titleAndArtist, album},
                null, null, null, null, null);
    }

    public Cursor getPlayListAll() {
        return database.query(playListTableName, new String[]{titleAndArtist, album},
                null, null, null, null, null);
    }

    public Cursor getRecentPlayListAll() {
        return database.query(recentPlayListTableName, new String[]{titleAndArtist, album},
                null, null, null, null, null);
    }

    public Cursor getWishListAll() {
        return database.query(wishListTableName, new String[]{titleAndArtist, album},
                null, null, null, null, null);
    }
    ///////////////////////// CRUD method end /////////////////////////////

    public void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            database.close(); // close the database connection
        }
    }
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createQuery = null;
            createQuery = "CREATE TABLE IF NOT EXISTS " + favoriteListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + playListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + recentPlayListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + wishListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
