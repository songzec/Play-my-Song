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
public class AccountProfileActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceActivity) {
        super.onCreate(savedInstanceActivity);
        setContentView(R.layout.accountprofilepage);
        Button ok = (Button) findViewById(R.id.apok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AccountProfileActivity.this)
                        .setTitle("Modify information successfully!")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(AccountProfileActivity.this, SettingActivity.class);
                                        startActivity(intent);
                                    }
                                }).create().show();
            }
        });
        Button cancel = (Button) findViewById(R.id.apcancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
