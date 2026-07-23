package com.example.ho_06_mada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class StartActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    private Button next;
    MediaPlayer myMus = null;
    @Override
    protected void onResume(){
        super.onResume();
        if (myMus != null) myMus.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        if (myMus != null) myMus.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioButton1=(RadioButton)findViewById(R.id.radioButton1);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4);
        radioButton5=(RadioButton)findViewById(R.id.radioButton5);
        next=(Button)findViewById(R.id.button6);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MethodActivity.class);
                if (radioButton1.isChecked()) {
                    intent.putExtra("startOption","1");
                } else if (radioButton2.isChecked()) {
                    intent.putExtra("startOption", "2");
                } else if (radioButton3.isChecked()) {
                    intent.putExtra("startOption", "3");
                } else if (radioButton4.isChecked()) {
                    intent.putExtra("startOption", "4");
                } else if (radioButton5.isChecked()) {
                    intent.putExtra("startOption", "5");
                }
                startActivity(intent);
            }
        });

        Switch soundSw = (Switch) findViewById(R.id.switch4);
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