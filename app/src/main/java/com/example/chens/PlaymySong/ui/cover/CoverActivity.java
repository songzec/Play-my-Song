package com.example.chens.PlaymySong.ui.cover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chens.PlaymySong.ui.main_page.MainActivity;
import com.example.chens.PlaymySong.R;
/**
 * Created by Songze Chen on 2016/4/3.
 */
public class CoverActivity extends AppCompatActivity {
    private Button loginButton, toSignUpActivityButton, justPlayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cover_coveractivity);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        toSignUpActivityButton = (Button) findViewById(R.id.toSignUpActivityButton);
        toSignUpActivityButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        justPlayButton = (Button) findViewById(R.id.justPlayButton);
        justPlayButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
