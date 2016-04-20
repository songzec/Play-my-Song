package com.example.chens.PlaymySong.ui.main_page.recommend_page;

import com.example.chens.PlaymySong.ui.main_page.SortedFragment;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Songze Chen on 2016/4/19.
 */
public class RecommendArtistFragment extends SortedRecommendFragment {
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
