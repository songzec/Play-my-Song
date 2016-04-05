package com.example.chens.PlaymySong.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.chens.PlaymySong.R;

/**
 * Created by Xiaotong Liang on 2016/4/3.
 */
public class HelpActivity extends Activity {
    protected GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helppage);
        Button button = (Button) findViewById(R.id.helpbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mGestureDetector = new GestureDetector(HelpActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e2.getRawX() - e1.getRawX() > 200) {
                    Intent intent = new Intent(HelpActivity.this, SettingActivity.class);
                    startActivity(intent);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
}
