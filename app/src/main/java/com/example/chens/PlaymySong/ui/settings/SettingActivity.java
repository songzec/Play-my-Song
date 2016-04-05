package com.example.chens.PlaymySong.ui.settings;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.chens.PlaymySong.R;

/**
 * Created by Xiaotong Liang on 2016/4/3.
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingpage);
        SettingListFragment lstFragment = (SettingListFragment) getSupportFragmentManager().findFragmentByTag("listFragment");
        if (lstFragment == null) {
            lstFragment = new SettingListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, lstFragment, "listFragment");
            transaction.commit();
        }
    }
}
