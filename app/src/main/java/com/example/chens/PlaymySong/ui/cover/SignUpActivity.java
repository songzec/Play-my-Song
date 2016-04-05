package com.example.chens.PlaymySong.ui.cover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chens.PlaymySong.R;

public class SignUpActivity extends AppCompatActivity {
    private Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
