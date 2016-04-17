package com.example.chens.PlaymySong.ui.main_page.playing_page;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chens.PlaymySong.DBLayout.MyLocalDB;
import com.example.chens.PlaymySong.R;
import com.example.chens.PlaymySong.ui.settings.SettingActivity;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayingBottomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayingBottomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayingBottomFragment extends Fragment {
    // static symbol
    private static final String ALL_SONGS = "allSongs";

    private View view;
    private Toast toast;
    // save all songs name
    private ArrayList<String> allSongs;
    MediaPlayer mediaPlayer = null;
    // all buttons
    private ImageButton setting;
    private ImageButton album;
    private ImageButton backward;
    private ImageButton forward;
    private ImageButton play;

    // all text views
    private TextView timePlayed;
    private TextView songLength;
    private TextView titleBot;
    private TextView artistBot;
    private ListView listView;

    private OnFragmentInteractionListener mListener;

    private Cursor cursor;
    public PlayingBottomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param allSongs all songs in the playing list
     * @return A new instance of fragment PlayingBottomFragment.
     */
    public static PlayingBottomFragment newInstance(ArrayList<String> allSongs) {
        PlayingBottomFragment fragment = new PlayingBottomFragment();
        Bundle args = new Bundle();
        args.putSerializable(ALL_SONGS, allSongs);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allSongs = (ArrayList<String>)getArguments().getSerializable(ALL_SONGS);
            System.out.println(allSongs);
            System.out.println(allSongs.get(0));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.playing_page_playingbottomfragment, container, false);

        // initialize buttons
        setting = (ImageButton)view.findViewById(R.id.setting);
        album = (ImageButton)view.findViewById(R.id.album);
        backward = (ImageButton)view.findViewById(R.id.backward);
        forward = (ImageButton)view.findViewById(R.id.forward);
        play = (ImageButton)view.findViewById(R.id.play);

        // initialize text views
        timePlayed = (TextView)view.findViewById(R.id.timePlayed);
        songLength = (TextView)view.findViewById(R.id.songLength);
        titleBot = (TextView)view.findViewById(R.id.titleBot);
        artistBot = (TextView)view.findViewById(R.id.artistBot);

        // initialize list view
        listView = (ListView)view.findViewById(R.id.listView);

        // set buttons' listeners
        setting.setOnClickListener(settingClick);
        album.setOnClickListener(albumClick);
        backward.setOnClickListener(backwardClick);
        forward.setOnClickListener(forwardClick);
        play.setOnClickListener(playClick);

        // set list view adapter
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(),
                             android.R.layout.simple_list_item_1,
                             (String[])allSongs.toArray(new String[allSongs.size()])) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        });


        // set on item click listener for list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view;
                String titleAndArtist = v.getText().toString();
                String title = titleAndArtist.split(" - ")[0];
                String artist = titleAndArtist.split(" - ")[1];
                MyLocalDB db = new MyLocalDB(getContext());

                db.open();
                cursor = db.getPlayListAll();
                System.out.println(cursor.getCount());
                cursor.moveToNext();
                System.out.println(cursor.getCount());
                while (!cursor.getString(0).equals(titleAndArtist)) {
                    cursor.moveToNext();
                }
                System.out.println(cursor.getCount());
                playMusic();

            }
        });


        return view;
    }

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
        void onFragmentInteraction(Uri uri);
    }


    private View.OnClickListener settingClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), SettingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener albumClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener backwardClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (cursor != null) {
                sendToast(String.valueOf(cursor.getCount()));
                if (cursor.isFirst()) {
                    cursor.moveToLast();
                } else {
                    cursor.moveToPrevious();

                }
                playMusic();
            }
        }
    };

    private View.OnClickListener playClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mediaPlayer.isPlaying()) {
                play.setImageResource(R.drawable.play);
                mediaPlayer.pause();
            } else {
                play.setImageResource(R.drawable.setting_icon);
                mediaPlayer.start();
            }
        }
    };

    private View.OnClickListener forwardClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (cursor != null) {
                sendToast(String.valueOf(cursor.getCount()));
                if (cursor.isLast()) {
                    cursor.moveToFirst();
                } else {
                    cursor.moveToNext();
                }
                playMusic();
            }
        }
    };

    private void sendToast(String s) {

        if (toast == null){
            toast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        }

        toast.setText(s);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
    private void playMusic() {
        String titleAndArtist = cursor.getString(0);
        System.out.println(titleAndArtist);
        String title = titleAndArtist.split(" - ")[0];
        String artist = titleAndArtist.split(" - ")[1];
        sendToast("playing " + titleAndArtist);
        titleBot.setText(title);
        artistBot.setText(artist);

        int rawSourceID = Integer.parseInt(cursor.getString(1));
        AssetFileDescriptor afd;
        afd = getResources().openRawResourceFd(rawSourceID);

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
