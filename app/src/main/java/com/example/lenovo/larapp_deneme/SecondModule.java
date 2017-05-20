package com.example.lenovo.larapp_deneme;

import android.app.Activity;
import android.media.MediaPlayer;
import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.*;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SecondModule extends Activity {

    class resimler {

        public int resim1;
        public int resim2;

        public resimler(int r1, int r2) {
            resim1 = r1;
            resim2 = r2;
        }
    }

    resimler resimListesi[] = {
            new resimler(R.drawable.a1, R.drawable.a2),
            new resimler(R.drawable.i1, R.drawable.i2),
            new resimler(R.drawable.p1, R.drawable.p2),
            new resimler(R.drawable.s1, R.drawable.s2),
            new resimler(R.drawable.y1, R.drawable.y2)
    };

    int correct = 0, mod;
    int wrong = R.raw.error;
    int selection = -1;
    MediaPlayer mp;

    ImageButton[] imgbtns=new ImageButton[6];

    void shuffle(int arr[]) {
        Random rnd = new Random();

        for(int i=0; i<arr.length; i++) {
            int p1 = rnd.nextInt(arr.length);
            int p2 = rnd.nextInt(arr.length);

            int t = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = t;
        }
    }

    void set(ImageButton btn, int ind, int positions[], int indices[]) {
        if(positions[ind]%2 == 1)
            btn.setImageResource(resimListesi[indices[positions[ind]/2]].resim2);
        else
            btn.setImageResource(resimListesi[indices[positions[ind]/2]].resim1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_module);

        mp = MediaPlayer.create(SecondModule.this, R.raw.match);
        mp.start();
        mp = null;

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

        final int[] positions = new int[6];

        for(int i=0; i<6 ; i++)
            positions[i] = i;

        shuffle(positions);

        View.OnClickListener l = new View.OnClickListener() {
            public void onClick(View v) {
                if(selection == -1) {
                    selection = (int)v.getTag();
                    imgbtns[selection].setImageAlpha(128);
                }
                else {
                    int c = (int)v.getTag();
                    imgbtns[selection].setImageAlpha(255);

                    if(positions[c] / 2 == positions[selection] / 2) {
                        imgbtns[c].setVisibility(View.INVISIBLE);
                        imgbtns[selection].setVisibility(View.INVISIBLE);

                        correct++;
                        if(correct == 3)

                            EnterActivity.change(SecondModule.this);
                    }
                    selection = -1;
                }
            }
        };

        for(int i=0; i<6 ; i++) {
            set(imgbtns[i], i, positions, indices);
            imgbtns[i].setTag(i);
            imgbtns[i].setOnClickListener(l);
        }
    }
}