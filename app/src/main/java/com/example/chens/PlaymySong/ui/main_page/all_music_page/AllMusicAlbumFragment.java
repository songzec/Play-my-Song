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
 * Use the {@link AllMusicAlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AllMusicAlbumFragment extends SortedAllMusicFragment {
    @Override
    public void sortNameList() {
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
