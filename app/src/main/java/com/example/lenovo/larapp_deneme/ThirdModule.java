package com.example.lenovo.larapp_deneme;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import java.util.Random;

public class ThirdModule extends Activity {

    MediaPlayer mp;
    Random rnd = new Random();
    ConstraintLayout layout;
    int[] sayi = {0, 0, 0};

    final int[] anaresim = {
            R.drawable.basketball1,R.drawable.car1,R.drawable.clothes1,R.drawable.footbal1,
            R.drawable.leptop1,R.drawable.moto1,R.drawable.okul1,R.drawable.sky1
            };

    final int[][] resim = {
            {
                    R.drawable.basketball2, R.drawable.basketball3
            },
            {
                    R.drawable.car2, R.drawable.car3
            },
            {
                    R.drawable.clothes2, R.drawable.clothes3
            },
            {
                    R.drawable.footbal2, R.drawable.footbal3
            },
            {
                    R.drawable.leptop2, R.drawable.leptop3
            },
            {
                    R.drawable.moto2, R.drawable.moto3
            },
            {
                    R.drawable.okul2, R.drawable.okul3
            },
            {
                    R.drawable.sky2, R.drawable.sky3
            }
    };


    final int[] arkaplan = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5};


    int correct, mod;
    int wrong = R.raw.error;


    ImageButton img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_module);
        layout = (ConstraintLayout) findViewById(R.id.third_module);

        img1 = (ImageButton) findViewById(R.id.imageButton);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        img3 = (ImageButton) findViewById(R.id.imageButton3);
        img4 = (ImageButton) findViewById(R.id.imageButton4);


        final MediaPlayer error = MediaPlayer.create(ThirdModule.this, R.raw.error);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (correct == 0) {

                    //EnterActivity.change(ThirdModule.this);
                    Intent intent = new Intent(ThirdModule.this, ThirdModule.class);
                    startActivity(intent);
                    finish();

                } else {

                    error.start();
                    error.stop();
                    error.reset();
                    error.release();

                }
            }


        });

        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (correct == 1) {

                    //EnterActivity.change(ThirdModule.this);
                    Intent intent = new Intent(ThirdModule.this, ThirdModule.class);
                    startActivity(intent);
                    finish();

                } else {

                    error.start();
                    error.stop();
                    error.reset();
                    error.release();

                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (correct == 2) {

                    //EnterActivity.change(ThirdModule.this);
                    Intent intent = new Intent(ThirdModule.this, ThirdModule.class);
                    startActivity(intent);
                    finish();

                } else {

                    error.start();
                    error.stop();
                    error.reset();
                    error.release();

                }
            }
        });
        nextlevel();
    }

    void nextlevel() {

        mod = rnd.nextInt(resim.length);
        sayi[0] = rnd.nextInt(resim[mod].length);

        while (true) {
            sayi[1] = rnd.nextInt(resim[mod].length);
            if (sayi[1] != sayi[0]) break;
        }

        while (true) {
            sayi[2] = rnd.nextInt(resim[mod].length);
            if (sayi[2] != sayi[0] && sayi[2] != sayi[1]) break;
        }

        correct = rnd.nextInt(3);

        layout.setBackgroundResource(arkaplan[mod]);

        Random rndm = new Random();
        int a1 = rndm.nextInt(anaresim.length);



        //img1.setImageResource(resim[mod][sayi[0]]);
        //img2.setImageResource(resim[mod][sayi[1]]);
        //img3.setImageResource(resim[mod][sayi[2]]);
        img4.setImageResource(anaresim[mod]);

    }
}


