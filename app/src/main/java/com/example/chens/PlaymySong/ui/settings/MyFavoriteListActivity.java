package com.example.chens.PlaymySong.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.chens.PlaymySong.R;

/**
 * Created by Xiaotong Liang on 2016/4/3.
 */
public class MyFavoriteListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfavoritelistpage);
        ImageButton imageButton=(ImageButton)findViewById(R.id.mflbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFavoriteListActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

    }
}
