package com.example.sayan.kindo.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.sayan.kindo.R;

import java.io.IOException;

/**
 * Created by 1605476 and 02-Oct-18
 **/

public class MusicService extends Service
{

    MediaPlayer player;
   // MusicBinder binder=new MusicBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

  /*  public class MusicBinder extends Binder
    {
         public MusicService getService()
         {
             return MusicService.this;
         }
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this,R.raw.eine);
        player.setLooping(true);
        player.setVolume(100,100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        player.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
    }


    @Override
    public void onDestroy()
    {
        player.stop();
        player.release();
        super.onDestroy();
    }
}
