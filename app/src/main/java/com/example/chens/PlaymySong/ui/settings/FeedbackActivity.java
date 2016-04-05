package com.example.chens.PlaymySong.ui.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chens.PlaymySong.R;

/**
 * Created by Xiaotong Liang on 2016/4/3.
 */
public class FeedbackActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceActivity) {
        super.onCreate(savedInstanceActivity);
        setContentView(R.layout.feedbackpage);
        Button send = (Button) findViewById(R.id.fbsend);
        Button cancel = (Button) findViewById(R.id.fbcancel);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(FeedbackActivity.this)
                        .setTitle("Your request has been sent!")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(FeedbackActivity.this, SettingActivity.class);
                                        startActivity(intent);
                                    }
                                }).create().show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
