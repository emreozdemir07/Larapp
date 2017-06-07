package com.example.lenovo.larapp_deneme;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThirdModule extends Activity implements View.OnClickListener {

    private static final String TAG = ThirdModule.class.getSimpleName();
    private Random rnd = new Random();
    private ConstraintLayout layout;
    private ImageButton imageButton, imageButton2, imageButton3, imageButton4;
    private MediaPlayer error,different;
    private List<Integer> numbers = new ArrayList<>();
    private boolean isMediaReady = true;
    int correct, mod;

    final int[] anaresim = {
            R.drawable.basketball1, R.drawable.car1, R.drawable.clothes1, R.drawable.footbal1,
            R.drawable.leptop1, R.drawable.moto1, R.drawable.okul1, R.drawable.sky1
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_module);
        layout = (ConstraintLayout) findViewById(R.id.third_module);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(this);
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);

        differentsound();

        nextLevel();
    }

    void nextLevel() {

        mod = rnd.nextInt(resim.length - 1);
        Log.i(TAG, "MOD : " + mod);
        imageButton4.setImageResource(anaresim[mod]);


        calcucateNumbers(mod);

        rnd = new Random();
        correct = rnd.nextInt(3);

        Log.i(TAG, "Correct :" + correct);

        layout.setBackgroundResource(R.drawable.thirdmodulebackground);

        int selectCorrectPic;
        while (true) {
            rnd = new Random();
            selectCorrectPic = rnd.nextInt(anaresim.length);

            if (selectCorrectPic != mod) {
                break;
            }

        }
        if (correct == 0) {
            imageButton.setImageResource(resim[selectCorrectPic][rnd.nextInt(resim[selectCorrectPic].length)]);
            imageButton2.setImageResource(resim[mod][numbers.get(0)]);
            imageButton3.setImageResource(resim[mod][numbers.get(1)]);
        } else if (correct == 1) {
            imageButton2.setImageResource(resim[selectCorrectPic][rnd.nextInt(resim[selectCorrectPic].length)]);
            imageButton.setImageResource(resim[mod][numbers.get(0)]);
            imageButton3.setImageResource(resim[mod][numbers.get(1)]);
        } else if (correct == 2) {
            imageButton3.setImageResource(resim[selectCorrectPic][rnd.nextInt(resim[selectCorrectPic].length)]);
            imageButton.setImageResource(resim[mod][numbers.get(0)]);
            imageButton2.setImageResource(resim[mod][numbers.get(1)]);
        }


    }

    @Override
    public void onClick(View v) {

        boolean isErrorOccured = false;
        if (v.equals(imageButton)) {
            if (correct != 0) {
                errorSound();
                isErrorOccured = true;
            }

        } else if (v.equals(imageButton2)) {

            if (correct != 1) {
                errorSound();
                isErrorOccured = true;
            }

        } else if (v.equals(imageButton3)) {

            if (correct != 2) {
                errorSound();
                isErrorOccured = true;
            }

        }
        if(!isErrorOccured) {
            EnterActivity.change(ThirdModule.this);
        }

    }

    private void calcucateNumbers(int mod) {

        List<Integer> selectedList = new ArrayList<>();

        int listSize = resim[mod].length;
        Log.i(TAG, "List Size " + listSize);


        while (true) {
            if (listSize > 0) {
                rnd = new Random();
                int randomNumber = rnd.nextInt(resim[mod].length);
                Log.i(TAG, "Random Number : " + randomNumber);
                if (!selectedList.contains(randomNumber)) {
                    numbers.add(randomNumber);
                    selectedList.add(randomNumber);
                    Log.i(TAG, "Adding to list : " + randomNumber);
                    listSize--;
                } else {
                    Log.i(TAG, "Number already in list : " + randomNumber);

                }
            } else {
                Log.i(TAG, "List generated : " + numbers.toString());
                break;
            }
        }

    }

    private synchronized void errorSound() {

        if (isMediaReady) {
            error = MediaPlayer.create(ThirdModule.this, R.raw.error);
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

    private synchronized void differentsound() {

        if (isMediaReady) {
            different = MediaPlayer.create(ThirdModule.this, R.raw.different);
            different.start();
            isMediaReady = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    different.stop();
                    different.release();
                    isMediaReady = true;
                }
            }, 2100L);
        }
    }

}