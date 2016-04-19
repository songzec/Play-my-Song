package com.example.chens.PlaymySong.ui.main_page.all_music_page;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chens.PlaymySong.R;
import com.example.chens.PlaymySong.entities.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Created by Songze Chen on 2016/4/3.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlbumFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AlbumFragment extends AllMusicSubFragment {
    @Override
    public void sortList() {
        Collections.sort(allSongsName, new Comparator<String>() {
            @Override
            public int compare(String song1, String song2) {
                String album1 = song1.split(" - ")[2];
                String album2 = song2.split(" - ")[2];
                return album1.compareToIgnoreCase(album2);
            }
        });
    }
}
