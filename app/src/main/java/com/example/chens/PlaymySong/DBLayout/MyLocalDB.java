package com.example.chens.PlaymySong.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chens.PlaymySong.entities.Song;

import java.util.ArrayList;

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
    private final String rawSourceID = "rawSourceID";
    private SQLiteDatabase database;
    private final String SINGLE_QUOTES = "single_quotes";
    private final String DOUBLE_QUOTES = "double_quotes";
    private final String SPACE = "space";
    public MyLocalDB(Context context) {
        String dbName = "myDatabase";
        databaseOpenHelper =
                new DatabaseOpenHelper(context, dbName, null, 1);
    }

    ///////////////////////// CRUD method begin /////////////////////////////
    public void addToFavoriteList(Song song) {
        String title = song.getTitle();
        String artist = song.getArtist();
        if (containsInFavoriteList(title, artist)) {
            deleteFromFavoriteList(title, artist);
        }

        ContentValues newRecords = new ContentValues();
        String key = toQuotationFreeString(title + " - " + artist);
        newRecords.put(titleAndArtist, key);
        newRecords.put(album, song.getAlbum());
        newRecords.put(rawSourceID, song.getRawSourceID());
        open();
        database.insert(favoriteListTableName, null, newRecords);
        close();
    }

    public void addToPlayList(Song song) {
        String title = song.getTitle();
        String artist = song.getArtist();
        if (containsInPlayList(title, artist)) {
            deleteFromPlayList(title, artist);
        }

        ContentValues newRecords = new ContentValues();
        String key = toQuotationFreeString(title + " - " + artist);
        newRecords.put(titleAndArtist, key);
        newRecords.put(album, song.getAlbum());
        newRecords.put(rawSourceID, song.getRawSourceID());
        open();
        database.insert(playListTableName, null, newRecords);
        close();
    }

    public void addToRecentPlayList(Song song) {
        String title = song.getTitle();
        String artist = song.getArtist();
        if (containsInRecentPlayList(title, artist)) {
            deleteFromRecentPlayList(title, artist);
        }

        ContentValues newRecords = new ContentValues();
        String key = toQuotationFreeString(title + " - " + artist);
        newRecords.put(titleAndArtist, key);
        newRecords.put(album, song.getAlbum());
        newRecords.put(rawSourceID, song.getRawSourceID());
        open();
        database.insert(recentPlayListTableName, null, newRecords);
        close();
    }

    public void addToWishList(Song song) {
        deleteFromWishList(song.getTitle(), song.getArtist());
        ContentValues newRecords = new ContentValues();
        String key = toQuotationFreeString(song.getTitle() + " - " + song.getArtist());
        newRecords.put(titleAndArtist, key);
        newRecords.put(album, song.getAlbum());
        open();
        database.insert(wishListTableName, null, newRecords);
        close();
    }

    public void deleteFromFavoriteList(String title, String artist) {
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        database.delete(favoriteListTableName, titleAndArtist + "='" + key + "'", null);
        close();
    }

    public void deleteFromPlayList(String title, String artist) {
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        database.delete(playListTableName, titleAndArtist + "='" + key + "'", null);
        close();
    }

    public void deleteFromRecentPlayList(String title, String artist) {
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        database.delete(recentPlayListTableName, titleAndArtist + "='" + key + "'", null);
        close();
    }

    public void deleteFromWishList(String title, String artist) {
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        database.delete(wishListTableName, titleAndArtist + "='" + key + "'", null);
        close();
    }

    public ArrayList<Song> getFavoriteListAll() {
        open();
        Cursor cursor =  database.query(favoriteListTableName, null, null, null, null, null, null);
        ArrayList<Song> songs = new ArrayList<Song>();
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            String titleAndArtist = revertQuotationString(cursor.getString(0));
            String title = titleAndArtist.split(" - ")[0];
            String artist = titleAndArtist.split(" - ")[1];
            int rawSourceID = Integer.parseInt(cursor.getString(1));
            String album = cursor.getString(2);
            songs.add(new Song(title, artist, album, rawSourceID));
            cursor.moveToNext();
        }
        close();
        return songs;
    }

    public ArrayList<Song> getPlayListAll() {
        open();
        Cursor cursor =  database.query(playListTableName, null, null, null, null, null, null);
        ArrayList<Song> songs = new ArrayList<Song>();

        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            String titleAndArtist = revertQuotationString(cursor.getString(0));
            String title = titleAndArtist.split(" - ")[0];
            String artist = titleAndArtist.split(" - ")[1];
            int rawSourceID = Integer.parseInt(cursor.getString(1));
            String album = cursor.getString(2);
            songs.add(new Song(title, artist, album, rawSourceID));
            cursor.moveToNext();
        }
        close();
        return songs;
    }

    public ArrayList<Song> getRecentPlayListAll() {
        open();
        Cursor cursor =  database.query(recentPlayListTableName, null, null, null, null, null, null);
        ArrayList<Song> songs = new ArrayList<Song>();
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            String titleAndArtist = revertQuotationString(cursor.getString(0));
            String title = titleAndArtist.split(" - ")[0];
            String artist = titleAndArtist.split(" - ")[1];
            int rawSourceID = Integer.parseInt(cursor.getString(1));
            String album = cursor.getString(2);
            songs.add(new Song(title, artist, album, rawSourceID));
            cursor.moveToNext();
        }
        close();
        return songs;
    }

    public ArrayList<Song> getWishListAll() {
        open();
        Cursor cursor =  database.query(wishListTableName, null, null, null, null, null, null);
        ArrayList<Song> songs = new ArrayList<Song>();
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            String titleAndArtist = revertQuotationString(cursor.getString(0));
            String title = titleAndArtist.split(" - ")[0];
            String artist = titleAndArtist.split(" - ")[1];

            songs.add(new Song(title, artist));
            cursor.moveToNext();
        }
        close();
        return songs;
    }

    public Cursor getFavoriteListOne(String title, String artist) {
        return database.query(favoriteListTableName, null, titleAndArtist + "='" + title + " - " + artist + "'", null, null, null, null);
    }

    public Cursor getPlayListOne(String title, String artist) {
        return database.query(playListTableName, null, titleAndArtist + "='" + title + " - " + artist + "'", null, null, null, null);
    }

    public Cursor getRecentPlayListOne(String title, String artist) {
        return database.query(recentPlayListTableName, null, titleAndArtist + "='" + title + " - " + artist + "'", null, null, null, null);
    }

    public Cursor getWishListOne(String title, String artist) {
        return database.query(wishListTableName, null, titleAndArtist + "='" + title + " - " + artist + "'", null, null, null, null);
    }

    public boolean containsInFavoriteList(String title, String artist) {
        boolean contain;
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        Cursor cursor =  database.query(favoriteListTableName, null, titleAndArtist + "='" + key + "'", null, null, null, null);
        contain = (cursor.getCount() != 0);
        close();

        return contain;
    }

    public boolean containsInPlayList(String title, String artist) {
        boolean contain;
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        Cursor cursor =  database.query(playListTableName, null, titleAndArtist + "='" + key + "'", null, null, null, null);
        contain = (cursor.getCount() != 0);
        close();

        return contain;
    }

    public boolean containsInRecentPlayList(String title, String artist) {
        boolean contain;
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        Cursor cursor =  database.query(recentPlayListTableName, null, titleAndArtist + "='" + key + "'", null, null, null, null);
        contain = (cursor.getCount() != 0);
        close();

        return contain;
    }

    public boolean containsInWishList(String title, String artist) {
        boolean contain;
        open();
        String key = toQuotationFreeString(title + " - " + artist);
        Cursor cursor =  database.query(wishListTableName, null, titleAndArtist + "='" + key + "'", null, null, null, null);
        contain = (cursor.getCount() != 0);
        close();

        return contain;
    }
    ///////////////////////// CRUD method end /////////////////////////////

    /**
     * Help function for storing quotation mark containing String.
     * @param raw
     * @return the String stored in database. e.g. "PullinSINGLE_QUOTES Me In  - Wyclef Jean"
     */
    private String toQuotationFreeString(String raw) {
        return raw.replace("'", SINGLE_QUOTES).replace("\"", DOUBLE_QUOTES).replace(" ", SPACE);
    }

    /**
     * Help function for storing quotation mark containing String.
     * @param quotationFree
     * @return the raw String. e.g. "Pullin' Me In  - Wyclef Jean"
     */
    private String revertQuotationString(String quotationFree) {
        return quotationFree.replace(SINGLE_QUOTES, "'").replace(DOUBLE_QUOTES, "\"").replace(SPACE, " ");
    }

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

            String createQuery = null, deleteQuery = null;
            createQuery = "CREATE TABLE IF NOT EXISTS " + favoriteListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + rawSourceID + " INTEGER,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE " + playListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + rawSourceID + " INTEGER,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + recentPlayListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + rawSourceID + " INTEGER,"
                    + album + " TEXT);";
            db.execSQL(createQuery);

            createQuery = "CREATE TABLE IF NOT EXISTS " + wishListTableName + " ("
                    + titleAndArtist + " TEXT PRIMARY KEY,"
                    + rawSourceID + " INTEGER,"
                    + album + " TEXT);";
            db.execSQL(createQuery);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
