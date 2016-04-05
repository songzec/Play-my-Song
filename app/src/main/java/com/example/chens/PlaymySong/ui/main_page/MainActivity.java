package com.example.chens.PlaymySong.ui.main_page;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.chens.PlaymySong.ui.main_page.list_fragment.ListFragment;
import com.example.chens.PlaymySong.R;
import com.example.chens.PlaymySong.ui.main_page.recommend_fragment.RecommendFragment;
import com.example.chens.PlaymySong.ui.main_page.top20_fragment.Top20Fragment;
import com.example.chens.PlaymySong.ui.main_page.all_music_fragment.AllMusicFragment;

public class MainActivity extends Activity implements View.OnClickListener {

    private Fragment allMusicFragment = new AllMusicFragment();
    private Fragment recommendFragment = new RecommendFragment();
    private Fragment listFragment = new ListFragment();
    private Fragment top20Fragment = new Top20Fragment();

    private FrameLayout allMusicLayout, recommendLayout, listLayout,
            top20Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initClickEvent();

        initFragment();

    }

    private void initFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (!allMusicFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, allMusicFragment);
            fragmentTransaction.hide(allMusicFragment);
        }
        if (!recommendFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, recommendFragment);
            fragmentTransaction.hide(recommendFragment);
        }
        if (!listFragment.isAdded()) {
            fragmentTransaction.add(R.id.content, listFragment);
            fragmentTransaction.hide(listFragment);
        }
        if (!top20Fragment.isAdded()) {
            fragmentTransaction.add(R.id.content, top20Fragment);
            fragmentTransaction.hide(top20Fragment);
        }
        hideAllFragment(fragmentTransaction);

        fragmentTransaction.show(allMusicFragment);
        fragmentTransaction.commit();
    }


    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.hide(allMusicFragment);
        fragmentTransaction.hide(recommendFragment);
        fragmentTransaction.hide(listFragment);
        fragmentTransaction.hide(top20Fragment);
    }

    private void initClickEvent() {
        allMusicLayout.setOnClickListener(this);
        recommendLayout.setOnClickListener(this);
        listLayout.setOnClickListener(this);
        top20Layout.setOnClickListener(this);
    }


    private void initView() {
        allMusicLayout = (FrameLayout) findViewById(R.id.allMusicLayout);
        recommendLayout = (FrameLayout) findViewById(R.id.recommendLayout);
        listLayout = (FrameLayout) findViewById(R.id.listLayout);
        top20Layout = (FrameLayout) findViewById(R.id.top20Layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allMusicLayout:
                clickTab(allMusicFragment);
                break;

            case R.id.recommendLayout:
                clickTab(recommendFragment);
                break;

            case R.id.listLayout:
                clickTab(listFragment);
                break;

            case R.id.top20Layout:
                clickTab(top20Fragment);
                break;

            default:
                break;
        }
    }

    private void clickTab(Fragment tabFragment) {

        clearSelected();

        FragmentTransaction fragmentTransaction = getFragmentManager()
                .beginTransaction();

        hideAllFragment(fragmentTransaction);

        fragmentTransaction.show(tabFragment);

        fragmentTransaction.commit();

        changeTabStyle(tabFragment);

    }


    private void clearSelected() {
        if (!allMusicFragment.isHidden()) {
            allMusicLayout.setBackgroundColor(Color.parseColor("#535353"));
        }

        if (!recommendFragment.isHidden()) {
            recommendLayout.setBackgroundColor(Color.parseColor("#535353"));
        }

        if (!listFragment.isHidden()) {
            listLayout.setBackgroundColor(Color.parseColor("#535353"));
        }

        if (!top20Fragment.isHidden()) {
            top20Layout.setBackgroundColor(Color.parseColor("#535353"));
        }
    }


    private void changeTabStyle(Fragment tabFragment) {
        if (tabFragment instanceof AllMusicFragment) {
            allMusicLayout.setBackgroundColor(Color.parseColor("#34819D"));
        }

        if (tabFragment instanceof RecommendFragment) {
            recommendLayout.setBackgroundColor(Color.parseColor("#34819D"));
        }

        if (tabFragment instanceof ListFragment) {
            listLayout.setBackgroundColor(Color.parseColor("#34819D"));
        }

        if (tabFragment instanceof Top20Fragment) {
            top20Layout.setBackgroundColor(Color.parseColor("#34819D"));
        }
    }
}