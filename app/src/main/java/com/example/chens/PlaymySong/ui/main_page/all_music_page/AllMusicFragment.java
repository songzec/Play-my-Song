package com.example.chens.PlaymySong.ui.main_page.all_music_page;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chens.PlaymySong.DBLayout.MyLocalDB;
import com.example.chens.PlaymySong.R;
import com.example.chens.PlaymySong.entities.Song;
import com.example.chens.PlaymySong.ui.main_page.CustomNames;
import com.example.chens.PlaymySong.ui.main_page.playing_page.PlayingActivity;
import com.example.chens.PlaymySong.ui.settings.SettingActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
/**
 * Created by Songze Chen on 2016/4/3.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllMusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllMusicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TitleFragment titleFragment = new TitleFragment();
    private ArtistFragment artistFragment = new ArtistFragment();
    private AlbumFragment albumFragment = new AlbumFragment();
    private ImageView settings;
    private TextView importMySongs;
    private MediaMetadataRetriever mmr;
    private ArrayList<Song> songs;
    private View view;
    private FrameLayout titleLayout, singerLayout, albumLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AllMusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllMusicFragment newInstance(String param1, String param2) {
        AllMusicFragment fragment = new AllMusicFragment();
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
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.all_music_page_allmusicfragment, container, false);

        initFragment();
        importMySongs = (TextView) view.findViewById(R.id.import_my_songs);
        importMySongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Field[] fields = R.raw.class.getFields();
                    MyLocalDB db = new MyLocalDB(getContext());
                    ArrayList<String> songList = new ArrayList<String>();
                    for (Field field : fields) {
                        AssetFileDescriptor afd;
                        int rawSourceID = field.getInt(null);
                        afd = getResources().openRawResourceFd(rawSourceID);
                        mmr = new MediaMetadataRetriever();
                        mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                        String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                        String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                        Song song = new Song(title, artist, album, rawSourceID);
                        songList.add(title + " - " + artist);
                        db.addToPlayList(song);
                    }
                    Bundle myBundle = new Bundle();
                    myBundle.putSerializable(CustomNames.SONG_LIST, songList);
                    Intent intent = new Intent();
                    intent.setClass(v.getContext(), PlayingActivity.class);
                    intent.putExtras(myBundle);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        settings = (ImageView) view.findViewById(R.id.settingsView);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initFragment() {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (!titleFragment.isAdded()) {

            fragmentTransaction.add(R.id.subContent, titleFragment);
        }
        if (!artistFragment.isAdded()) {
            fragmentTransaction.add(R.id.subContent, artistFragment);
        }
        if (!albumFragment.isAdded()) {
            fragmentTransaction.add(R.id.subContent, albumFragment);
        }
        showThisFragment(titleFragment);
        fragmentTransaction.commit();
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FrameLayout title = (FrameLayout) getActivity().findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showThisFragment(titleFragment);
            }
        });

        FrameLayout singer = (FrameLayout) getActivity().findViewById(R.id.artist);
        singer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThisFragment(artistFragment);
            }
        });

        FrameLayout album = (FrameLayout) getActivity().findViewById(R.id.album);
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThisFragment(albumFragment);
            }
        });
    }

    private void showThisFragment(Fragment thisFragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(titleFragment);
        fragmentTransaction.hide(artistFragment);
        fragmentTransaction.hide(albumFragment);
        fragmentTransaction.show(thisFragment);
        fragmentTransaction.commit();
    }
}
