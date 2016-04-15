package com.example.chens.PlaymySong.ws.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Songze Chen on 2016/4/14.
 */
public class RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // do something

        return null;
    }
}
