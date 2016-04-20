package com.example.chens.PlaymySong.ui.main_page.all_music_page;

import android.app.Fragment;

import com.example.chens.PlaymySong.ui.main_page.SortedFragment;

import java.util.Collections;
import java.util.Comparator;
/**
 * Created by Songze Chen on 2016/4/3.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SortedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllMusicArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllMusicArtistFragment extends SortedAllMusicFragment {
    @Override
    public void sortNameList() {
        Collections.sort(allSongsName, new Comparator<String>() {
            @Override
            public int compare(String song1, String song2) {
                String artist1 = song1.split(" - ")[1];
                String artist2 = song2.split(" - ")[1];
                return artist1.compareToIgnoreCase(artist2);
            }
        });
    }
}
