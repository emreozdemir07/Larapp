package com.example.lenovo.larapp_deneme;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import java.util.Random;

public class SecondModule extends Activity implements View.OnClickListener {

    private boolean isMediaReady = true;
    private MediaPlayer error;

    private static final String TAG = SecondModule.class.getSimpleName();

    private Resimler resimListesi[] = {
            new Resimler(R.drawable.a1, R.drawable.a2),
            new Resimler(R.drawable.i1, R.drawable.i2),
            new Resimler(R.drawable.p1, R.drawable.p2),
            new Resimler(R.drawable.s1, R.drawable.s2),
            new Resimler(R.drawable.y1, R.drawable.y2),
            new Resimler(R.drawable.pilot1, R.drawable.pilot2),
            new Resimler(R.drawable.dentist1, R.drawable.dentist2),
    };

    private int correct = 0, mod;
    private int wrong = R.raw.error;
    private int selection = -1;
    private MediaPlayer mp;
    private ImageButton[] imgbtns=new ImageButton[6];
    private  int[] positions;

    private Handler mediaHandler = new Handler();
    private Runnable mediaWatchdogRunnable = new Runnable() {
        @Override
        public void run() {

            if(mp!=null){
                mp.stop();
                mp.release();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_module);

        mp = MediaPlayer.create(SecondModule.this, R.raw.match);
        mp.start();

        mediaHandler.postDelayed(mediaWatchdogRunnable,3000L);


        imgbtns[0] = (ImageButton) findViewById(R.id.ib1);
        imgbtns[1] = (ImageButton) findViewById(R.id.ib2);
        imgbtns[2] = (ImageButton) findViewById(R.id.ib3);
        imgbtns[3] = (ImageButton) findViewById(R.id.ib4);
        imgbtns[4] = (ImageButton) findViewById(R.id.ib5);
        imgbtns[5] = (ImageButton) findViewById(R.id.ib6);

        int[] indices = new int[resimListesi.length];

        for(int i=0; i<resimListesi.length ; i++)
            indices[i] = i;

        shuffle(indices);

        positions = new int[6];

        for(int i=0; i<6 ; i++)
            positions[i] = i;

        shuffle(positions);

        for(int i=0; i<6 ; i++) {
            set(imgbtns[i], i, positions, indices);
            imgbtns[i].setTag(i);
            imgbtns[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(selection == -1) {
            selection = (int)v.getTag();
            imgbtns[selection].setImageAlpha(128);
        }
        else {
            int c = (int)v.getTag();
            imgbtns[selection].setImageAlpha(255);

            if(positions[c] / 2 != positions[selection] / 2) {
                errorSound();
            }

            if(positions[c] / 2 == positions[selection] / 2) {
                imgbtns[c].setVisibility(View.INVISIBLE);
                imgbtns[selection].setVisibility(View.INVISIBLE);

                correct++;
                Log.i(TAG,"Correct : " + correct);
                if(correct == 3) {
                    EnterActivity.change(SecondModule.this);
                }
            }
            selection = -1;

        }
    }

    private class Resimler {

        public int resim1;
        public int resim2;

        public Resimler(int r1, int r2) {
            resim1 = r1;
            resim2 = r2;
        }
    }

    private void shuffle(int arr[]) {
        Random rnd = new Random();

        for(int i=0; i<arr.length; i++) {
            int p1 = rnd.nextInt(arr.length);
            int p2 = rnd.nextInt(arr.length);

            int t = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = t;
        }
    }

    private void set(ImageButton btn, int ind, int positions[], int indices[]) {
        if(positions[ind]%2 == 1)
            btn.setImageResource(resimListesi[indices[positions[ind]/2]].resim2);
        else
            btn.setImageResource(resimListesi[indices[positions[ind]/2]].resim1);

    }

    private synchronized void errorSound() {

        if (isMediaReady) {
            error = MediaPlayer.create(SecondModule.this, R.raw.error);
            error.start();
            isMediaReady = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    error.stop();
                    error.release();
                    isMediaReady = true;
                }
            }, 500L);
        }
    }
}