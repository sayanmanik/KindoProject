package com.example.sayan.kindo;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sayan.kindo.Service.MusicService;
import com.plattysoft.leonids.ParticleSystem;

public class RainActivity extends AppCompatActivity
{
    //MusicService mService;
    Intent intent;
    ImageView land_image,cloud_image;
    int initial;
    TextView txt;

   // boolean mBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);

        //intent =new Intent(this,MusicService.class);
       // startService(intent);

        land_image=findViewById(R.id.land);
        cloud_image=findViewById(R.id.cloud_image);
        initial= (int) System.currentTimeMillis();
        txt=findViewById(R.id.text);
        cloud_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final ParticleSystem p=new ParticleSystem(RainActivity.this, 80, R.drawable.rain_drop, 10000);
                p.setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f);
                p.setAcceleration(0.00005f, 90);
                p.emitWithGravity(findViewById(R.id.cloud_image), Gravity.BOTTOM, 8);

             Runnable r = new Runnable() {
                 @Override
                 public void run()
                 {
                     land_image.setImageResource(R.drawable.green_tree);
                     p.stopEmitting();
                     txt.setText("Now the barren land turns to fertile!!");
                     cloud_image.setImageResource(R.drawable.sunny_day);
                     cloud_image.setBackgroundColor(getResources().getColor(R.color.white));
                 }
             };
             land_image.postDelayed(r,10000);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        intent =new Intent(this,MusicService.class);
        startService(intent);

        //Intent intent=new Intent(RainActivity.this,MusicService.class);
        //bindService(intent,connection,Context.BIND_AUTO_CREATE);

    }

    /*private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder= (MusicService.MusicBinder) service;
            mService=  binder.getService();
            mBound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound=false;
        }
    };*/

   /* protected void onPause() {
        super.onPause();
        /*if(mBound)
        {
            unbindService(connection);
            mBound=false;
        }
        stopService(intent);
    }*/

    @Override
    protected void onStop() {
        super.onStop();
        /*if(mBound)
        {
            unbindService(connection);
            mBound=false;
        }*/
        stopService(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if(mBound)
        {
            unbindService(connection);
            mBound=false;
        }*/
        stopService(intent);
    }
}