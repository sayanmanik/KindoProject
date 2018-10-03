package com.example.sayan.kindo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sayan.kindo.Service.MusicService;

public class MainActivity extends AppCompatActivity
{

    Button henButton,catButton,dogButton;
    ImageView iView;
    int initial_time;
    TextView textView;
    Intent intent;
    Vibrator vibe;

    boolean mBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        henButton=findViewById(R.id.henButton);
        catButton=findViewById(R.id.catButton);
        dogButton=findViewById(R.id.dogButton);

        textView=findViewById(R.id.textView4);

        vibe=(Vibrator) getSystemService(VIBRATOR_SERVICE);



        //meowView=findViewById(R.id.imageView2);
        iView=findViewById(R.id.imageView);

        //i =new Intent(this,MusicService.class);
        //startService(i);

       Runnable r= new Runnable() {
            @Override
            public void run()
            {
                iView.setImageResource(R.drawable.cat_eye);
            }
        };
        iView.postDelayed(r,10000);

        catButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int time= (int) System.currentTimeMillis();
                if(time-initial_time>10000)
                {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Meowwwwwww!!!!!");
                    iView.setImageResource(R.drawable.cat_image);
                    //meowView.setImageResource(R.drawable.meow);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i=new Intent(MainActivity.this,RainActivity.class);
                            startActivity(i);
                            MainActivity.this.finish();
                        }
                    },5000);
                }
                else
                {
                    vibe.vibrate(100);
                    textView.setText("Wait upto 10 seconds");
                   // iView.setImageResource(R.drawable.cat_eye);
                    textView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });

        henButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time= (int) System.currentTimeMillis();

                if(time-initial_time>10000)
                {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Sorry wrong answer");
                    /*iView.setImageResource(R.drawable.cat_image);
                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    fragment.show(transaction,"dialog");
                    transaction.commit();*/



                }
                else
                {
                    textView.setText("Wait upto 10 seconds");
                    vibe.vibrate(100);
                    textView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                    textView.setVisibility(View.VISIBLE);
                }

            }
        });


        dogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time= (int) System.currentTimeMillis();

                if(time-initial_time>10000)
                {

                    textView.setVisibility(View.VISIBLE);
                    iView.setImageResource(R.drawable.cat_image);
                    textView.setText("Sorry wrong answer");
                    /*FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    fragment.show(transaction,"dialog");
                    transaction.commit();*/

                }
                else
                {
                    vibe.vibrate(100);
                    textView.setText("Wait upto 10 seconds");
                    textView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        intent=new Intent(MainActivity.this,MusicService.class);
        startService(intent);
        //bindService(intent,connection,Context.BIND_AUTO_CREATE);
    }

   /* private ServiceConnection connection=new ServiceConnection() {
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

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        initial_time= (int) System.currentTimeMillis();
        return super.onCreateView(parent, name, context, attrs);

    }
    protected void onPause() {
        super.onPause();
        /*if(mBound)
        {
            unbindService(connection);
            mBound=false;
        }*/
        stopService(intent);
    }

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