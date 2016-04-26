package com.example.chens.PlaymySong.ui.main_page.playing_page;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chens.PlaymySong.R;
import com.example.chens.PlaymySong.entities.Song;
import com.example.chens.PlaymySong.ui.main_page.CustomNames;
import com.example.chens.PlaymySong.ui.settings.SettingActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Xiao Ling on 2016/4/3.
 *
 * This fragment will show the current playing list.
 */
public class PlayingBottomFragment extends Fragment {

    private View view;
    private Toast toast;
    // save all songs name
    private ArrayList<String> allSongsName;

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
    private PlayingLyricFragment lyricFragment;
    private OnFragmentInteractionListener mListener;

    // seek bar
    private SeekBar musicProcess;

    // timer for update the seek bar
    private Timer timer;
    private TimerTask timerTask;

    // make sure the seek bar will not be update when user move it
    private boolean movingBar;

    public PlayingBottomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlayingBottomFragment.
     */
    public static PlayingBottomFragment newInstance() {
        PlayingBottomFragment fragment = new PlayingBottomFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateSongName();
        lyricFragment = (PlayingLyricFragment)((PlayingActivity)getActivity()).fragments.get(1);
    }

    private void generateSongName() {
        allSongsName = new ArrayList<>();
        for (Song song : PlayingResource.allSongs) {
            allSongsName.add(song.getTitle() + " - " + song.getArtist());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.playing_page_playingbottomfragment, container, false);

        // initialize buttons
        setting = (ImageButton)view.findViewById(R.id.setting);
        album = (ImageButton)view.findViewById(R.id.allMusicAlbumLayout);
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

        // initialize music seek bar
        musicProcess = (SeekBar)view.findViewById(R.id.musicProcess);
        musicProcess.setMax(280);

        movingBar = false;

        // set buttons' listeners
        setting.setOnClickListener(settingClick);
        album.setOnClickListener(albumClick);
        backward.setOnClickListener(backwardClick);
        forward.setOnClickListener(forwardClick);
        play.setOnClickListener(playClick);

        // set list view adapter
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1,
                (String[]) allSongsName.toArray(new String[allSongsName.size()])) {
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
                PlayingResource.currentSongPos = position;
                playMusic();

            }
        });

        // set listener for music seek bar
        musicProcess.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               // nothing
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                movingBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    int destPosition = seekBar.getProgress();
                    int totalTime = mediaPlayer.getDuration();
                    int maxLength = seekBar.getMax();
                    mediaPlayer.seekTo(totalTime*destPosition/maxLength);
                }
                movingBar = false;
            }
        });


        if (PlayingResource.currentSongPos != -1) {
            playMusic();
        }

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
            if (PlayingResource.currentSongPos != -1) {
                if (PlayingResource.currentSongPos == 0) {
                    PlayingResource.currentSongPos = PlayingResource.allSongs.size() - 1;
                } else {
                    PlayingResource.currentSongPos--;
                }
                playMusic();
            }
        }
    };

    private View.OnClickListener forwardClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (PlayingResource.currentSongPos != -1) {
                if (PlayingResource.currentSongPos == PlayingResource.allSongs.size() - 1) {
                    PlayingResource.currentSongPos = 0;
                } else {
                    PlayingResource.currentSongPos++;
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

    private void sendToast(String s) {
        if (toast == null){
            toast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        }
        toast.setText(s);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    private void playMusic() {
        Song currSong = PlayingResource.allSongs.get(PlayingResource.currentSongPos);
        String title = currSong.getTitle();
        String artist = currSong.getArtist();
        sendToast("playing " + allSongsName.get(PlayingResource.currentSongPos));
        titleBot.setText(title);
        artistBot.setText(artist);

        int rawSourceID = PlayingResource.allSongs.get(PlayingResource.currentSongPos).getRawSourceID();
        AssetFileDescriptor afd;
        afd = getResources().openRawResourceFd(rawSourceID);

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        try {
           // System.out.println("in try");

            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            //System.out.println("start 1");

            int totalTime = mediaPlayer.getDuration();
            int totalTimeMin = totalTime / 1000 / 60;
            int totalTimeSec = totalTime / 1000 - totalTimeMin * 60;

            timePlayed.setText("0:00 / " + totalTimeMin + ":" + String.format("%02d", totalTimeSec));

            mediaPlayer.start();
//            System.out.println("start 2");


            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(!movingBar) {
                        int curTime = mediaPlayer.getCurrentPosition();
                        int totalTime = mediaPlayer.getDuration();
                        int maxLength = 280;
                        double temp = (double)curTime / (double)totalTime * (double)maxLength;

//                        System.out.println("max: " + maxLength);
//                        System.out.println("curtime: " + curTime);

                        musicProcess.setProgress((int)temp);
                        final int curTimeMin = curTime / 1000 / 60;
                        final int curTimeSec = curTime / 1000 - curTimeMin * 60;

                        final int totalTimeMinCur = totalTime / 1000 / 60;
                        final int totalTimeSecCur = totalTime / 1000 - totalTimeMinCur * 60;

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timePlayed.setText(curTimeMin + ":"
                                                    + String.format("%02d", curTimeSec)
                                                    + " / " +  totalTimeMinCur + ":"
                                                    + String.format("%02d", totalTimeSecCur));
                            }
                        });
                    }
                }
            };
            int step = 100;
            timer.schedule(timerTask, 0, step);


            new Thread(lyricFragment.changeLyric).start();
            //System.out.println("start 3");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
