package com.example.lenovo.larapp_deneme;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class FirstModule extends Activity {

    private MediaPlayer wrongmusic, question;
    private boolean isMediaReady = true;
    Random rnd = new Random();
    ConstraintLayout layout;
    int[] sayi = {0, 0, 0};
    int correct, mod;
    ImageButton img1;
    ImageButton img2;
    ImageButton img3;

    final int[][] resim = {
            {
                    R.drawable.camel, R.drawable.cat, R.drawable.cow, R.drawable.crocodile, R.drawable.dog,
                    R.drawable.donkey, R.drawable.leon, R.drawable.monkey, R.drawable.mouse,
                    R.drawable.rabbit, R.drawable.sheep, R.drawable.taurus, R.drawable.turtle
            },
            {
                    R.drawable.winter, R.drawable.summer, R.drawable.spring, R.drawable.autumn
            },
            {
                    R.drawable.watermelon, R.drawable.strawberry, R.drawable.pineapple, R.drawable.pear,
                    R.drawable.orange, R.drawable.lemon, R.drawable.cherry, R.drawable.apricot, R.drawable.apple
            },
            {
                    R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                    R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine
            },
            {
                    R.drawable.touch, R.drawable.taste, R.drawable.smell, R.drawable.sight,
                    R.drawable.hearing
            }

    };
    final int[][] ses = {
            {
                    R.raw.camel, R.raw.cat, R.raw.cow, R.raw.crocodile, R.raw.dog,
                    R.raw.donkey, R.raw.leon, R.raw.monkey, R.raw.mouse,
                    R.raw.rabbit, R.raw.sheep, R.raw.taurus, R.raw.turtle
            },
            {
                    R.raw.winter, R.raw.summer, R.raw.spring, R.raw.autumn
            },
            {
                    R.raw.watermelon, R.raw.strawberry, R.raw.pineapple, R.raw.pear,
                    R.raw.orange, R.raw.lemon, R.raw.cherry, R.raw.apricot, R.raw.apple
            },
            {
                    R.raw.one, R.raw.two, R.raw.three, R.raw.four,
                    R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine
            },
            {
                    R.raw.touch, R.raw.taste, R.raw.smell, R.raw.sight,
                    R.raw.hearing
            }

    };

    final int[] arkaplan = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module);
        layout = (ConstraintLayout) findViewById(R.id.first_module);

        img1 = (ImageButton) findViewById(R.id.imageButton);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        img3 = (ImageButton) findViewById(R.id.imageButton3);

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (correct == 0) {

                    EnterActivity.change(FirstModule.this);

                } else {

                    wronganswer();

                }
            }


        });

        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (correct == 1) {

                    EnterActivity.change(FirstModule.this);

                } else {

                    wronganswer();

                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (correct == 2) {

                    EnterActivity.change(FirstModule.this);

                } else {

                    wronganswer();

                }
            }
        });

        nextlevel();
    }

    private synchronized void wronganswer() {

        if (isMediaReady) {
            wrongmusic = MediaPlayer.create(FirstModule.this, R.raw.error);
            wrongmusic.start();
            isMediaReady = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    wrongmusic.stop();
                    wrongmusic.release();
                    isMediaReady = true;
                }
            }, 500L);
        }
    }

    private synchronized void questions() {

        if (isMediaReady) {
            question = MediaPlayer.create(FirstModule.this, ses[mod][sayi[correct]]);
            question.start();
            isMediaReady = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    question.stop();
                    question.release();
                    isMediaReady = true;
                }
            }, 2100L);
        }
    }

    private synchronized void nextlevel() {

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
        questions();

        layout.setBackgroundResource(arkaplan[mod]);
        img1.setImageResource(resim[mod][sayi[0]]);
        img2.setImageResource(resim[mod][sayi[1]]);
        img3.setImageResource(resim[mod][sayi[2]]);


    }
}


