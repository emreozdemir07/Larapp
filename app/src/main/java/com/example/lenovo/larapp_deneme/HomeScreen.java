package com.example.lenovo.larapp_deneme;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class HomeScreen extends AppCompatActivity {

    ConstraintLayout layout;
    ImageButton play,exit,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        layout = (ConstraintLayout) findViewById(R.id.home_screen);
        play = (ImageButton) findViewById(R.id.play);
        about = (ImageButton) findViewById(R.id.about);

        final int[] arkaplan = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5};
        Random rnd = new Random();
        int mod = rnd.nextInt(6);

        layout.setBackgroundResource(arkaplan[mod]);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterActivity.change(HomeScreen.this);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this,"Emre ÖZDEMİR", Toast.LENGTH_LONG).show();
            }
        });
    }
}
