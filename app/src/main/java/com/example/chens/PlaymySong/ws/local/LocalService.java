package com.example.chens.PlaymySong.ws.local;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public class LocalService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // do something like playing music

        return null;
    }
}
