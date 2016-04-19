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

import com.example.chens.PlaymySong.entities.Song;
import com.example.chens.PlaymySong.ui.main_page.CustomNames;
import com.example.chens.PlaymySong.ui.main_page.playing_page.PlayingActivity;
import com.example.chens.PlaymySong.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TitleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by Songze Chen on 2016/4/3.
 */

public class AllMusicSubFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView listview;
    private View view;
    protected ArrayList<String> allSongsName = new ArrayList<String>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AllMusicSubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllMusicSubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllMusicSubFragment newInstance(String param1, String param2) {
        AllMusicSubFragment fragment = new AllMusicSubFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_title, container, false);

        listview = (ListView) view.findViewById(R.id.titleListView);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * to be override, default by title.
     */
    public void sortList() {
        Collections.sort(allSongsName, new Comparator<String>() {
            @Override
            public int compare(String song1, String song2) {
                String title1 = song1.split(" - ")[0];
                String title2 = song2.split(" - ")[0];
                return title1.compareToIgnoreCase(title2);
            }
        });
    }

    public void updateSongs(ArrayList<Song> songs) {
        allSongsName = new ArrayList<>();
        for (Song song : songs) {
            allSongsName.add(song.getTitle() + " - " + song.getArtist() + " - " + song.getAlbum());
        }

        sortList();

        listview.setAdapter(new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1,
                (String[]) allSongsName.toArray(new String[allSongsName.size()])) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), PlayingActivity.class);
                intent.putExtra(CustomNames.CURR_POSITION, position);
                startActivity(intent);
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
