package com.example.ho_06_mada;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class UsActivity extends AppCompatActivity {
    MediaPlayer myMus = null; // a field of MediaPlayer
    @Override
    protected void onResume(){ // callback method, when interacting with user
        super.onResume(); // always call superclass
        if (myMus != null) myMus.start(); // start playing
    }
    @Override
    protected void onPause(){ // callback method, inactive: when no interacting
        super.onPause(); // always call superclass
        if (myMus != null) myMus.pause(); // pause playing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us);

        Switch soundSw = (Switch) findViewById(R.id.switch3);
        soundSw.setChecked(true); // set the switch as Checked (ON) initially
        soundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { // is enabled
                    if (myMus != null) myMus.start(); // start playing
                } else { // is disabled
                    if (myMus != null) myMus.pause(); // pause playing
                }
            }
        });

        myMus = MediaPlayer.create(this, R.raw.bgmusic);
        myMus.setLooping(true);
    }
}