package com.example.chens.PlaymySong.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public class MyLocalDB {
    private DatabaseOpenHelper databaseOpenHelper;
    private final String favoriteListTableName = "FavoriteListTable";
    private final String playListTableName = "PlayListTable";
    private final String recentPlayListTableName = "RecentPlayListTable";
    private final String wishListTableName = "WishListTable";
    private final String titleAndSinger = "title_singer";
    private final String album = "album";
    private SQLiteDatabase database;

    public MyLocalDB(Context context) {
        String dbName = "myDatabase";
        databaseOpenHelper =
                new DatabaseOpenHelper(context, dbName, null, 1);
    }

    ///////////////////////// CRUD method begin /////////////////////////////
    public void addToFavoriteList(String title, String singer, String albumName) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndSinger, title + "+" + singer);
        newRecords.put(album, albumName);
        open();
        database.insert(favoriteListTableName, null, newRecords);
        close();
    }

    public void addToPlayList(String title, String singer, String albumName) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndSinger, title + "+" + singer);
        newRecords.put(album, albumName);
        open();
        database.insert(playListTableName, null, newRecords);
        close();
    }

    public void addToRecentPlayList(String title, String singer, String albumName) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndSinger, title + "+" + singer);
        newRecords.put(album, albumName);
        open();
        database.insert(recentPlayListTableName, null, newRecords);
        close();
    }

    public void addToWishList(String title, String singer, String albumName) {
        ContentValues newRecords = new ContentValues();
        newRecords.put(titleAndSinger, title + "+" + singer);
        newRecords.put(album, albumName);
        open();
        database.insert(wishListTableName, null, newRecords);
        close();
    }

    public void deleteFromFavoriteList(String title, String singer) {
        open();
        database.delete(favoriteListTableName, titleAndSinger + "=" + title + "+" + singer, null);
        close();
    }

    public void deleteFromPlayList(String title, String singer) {
        open();
        database.delete(playListTableName, titleAndSinger + "=" + title + "+" + singer, null);
        close();
    }

    public void deleteFromRecentPlayList(String title, String singer) {
        open();
        database.delete(recentPlayListTableName, titleAndSinger + "=" + title + "+" + singer, null);
        close();
    }

    public void deleteFromWishList(String title, String singer) {
        open();
        database.delete(wishListTableName, titleAndSinger + "=" + title + "+" + singer, null);
        close();
    }

    public Cursor getFavoriteListAll() {
        return database.query(favoriteListTableName, new String[]{titleAndSinger, album},
                null, null, null, null, null);
    }

    public Cursor getPlayListAll() {
        return database.query(playListTableName, new String[]{titleAndSinger, album},
                null, null, null, null, null);
    }

    public Cursor getRecentPlayListAll() {
        return database.query(recentPlayListTableName, new String[]{titleAndSinger, album},
                null, null, null, null, null);
    }

    public Cursor getWishListAll() {
        return database.query(wishListTableName, new String[]{titleAndSinger, album},
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
                    + titleAndSinger + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + playListTableName + " ("
                    + titleAndSinger + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + recentPlayListTableName + " ("
                    + titleAndSinger + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + wishListTableName + " ("
                    + titleAndSinger + " TEXT PRIMARY KEY,"
                    + album + " TEXT);";
            db.execSQL(createQuery);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
