package com.example.chens.PlaymySong.ui.main_page.playing_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chens.PlaymySong.R;

import java.util.ArrayList;

public class PlayingActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    // used for test, all songs' names
    private ArrayList<String> allSongs;
    // used for test, lyric
    private String lyric = "Hello, it's me\n" +
                    "I was wondering if after all these years you'd like to meet\n" +
                    "To go over everything\n" +
                    "They say that time's supposed to heal ya\n" +
                    "But I ain't done much healing\n" +
                    "\n" +
                    "Hello, can you hear me\n" +
                    "I'm in California dreaming about who we used to be\n" +
                    "When we were younger and free\n" +
                    "I've forgotten how it felt before\n the world fell at our feet\n"  +
                    "I've forgotten how it felt before\n the world fell at our feet\n" +
                    "I've forgotten how it felt before\n the world fell at our feet\n\n" +
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n";



    private SectionsPagerAdapter mSectionsPagerAdapter;

    // all fragments
    private ArrayList<Fragment> fragments;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        // used for test, add all songs' names
        allSongs = new ArrayList<String>();
        allSongs.add("Hello");
        allSongs.add("Billy Brown");
        allSongs.add("Born This Way");
        allSongs.add("Count On Me");
        allSongs.add("Grenade");
        allSongs.add("Happy Ending");
        allSongs.add("Heartbreak Hotel");
        allSongs.add("I Knew You Were Trouble");
        allSongs.add("Lemon Tree");
        allSongs.add("No Matter");
        allSongs.add("Runaway Baby");
        allSongs.add("Sorry");
        allSongs.add("Stay With Me");
        allSongs.add("Stronger");
        allSongs.add("Until You");

        // put all fragments in the list
        fragments = new ArrayList<Fragment>();
        fragments.add(PlayingBottomFragment.newInstance(allSongs));
        fragments.add(PlayingLyricFragment.newInstance(lyric));
        fragments.add(PlayingCommentFragment.newInstance());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                                                             fragments);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_playing, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("default");
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
