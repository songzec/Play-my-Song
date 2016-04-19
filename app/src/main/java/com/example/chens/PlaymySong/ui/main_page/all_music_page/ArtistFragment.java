package com.example.chens.PlaymySong.ui.main_page.all_music_page;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chens.PlaymySong.R;
import com.example.chens.PlaymySong.entities.Song;
import com.example.chens.PlaymySong.ui.main_page.CustomNames;
import com.example.chens.PlaymySong.ui.main_page.playing_page.PlayingActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Created by Songze Chen on 2016/4/3.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArtistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistFragment extends AllMusicSubFragment {
    @Override
    public void sortList() {
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
