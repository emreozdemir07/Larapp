package com.example.lenovo.larapp_deneme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EnterActivity extends AppCompatActivity {
    private static final int ENTER_TIME = 4500;
    private static final String TAG = EnterActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Timer myTimer=new Timer();
        TimerTask gorev = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(EnterActivity.this, HomeScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
                myTimer.cancel();
            }
        };

        myTimer.schedule(gorev, ENTER_TIME, ENTER_TIME);
    }

    public static void change(Activity act) {

        Random rnd = new Random();
        int mod = rnd.nextInt(3);

        Log.i(TAG,"MOD : " + mod);

        Intent intent = null;
        if(mod == 0)
        {
            intent = new Intent(act, FirstModule.class);
        }
        if(mod == 1)
        {
            intent = new Intent(act, SecondModule.class);
        }
        if(mod == 2)
        {
            intent = new Intent(act, ThirdModule.class);
        }

        act.startActivity(intent);
        act.finish();
    }
}