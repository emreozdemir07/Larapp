package com.example.lenovo.larapp_deneme;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    ConstraintLayout layout;
    ImageButton play,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        layout = (ConstraintLayout) findViewById(R.id.home_screen);
        play = (ImageButton) findViewById(R.id.play);
        about = (ImageButton) findViewById(R.id.about);

        layout.setBackgroundResource(R.drawable.homebackground);

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
