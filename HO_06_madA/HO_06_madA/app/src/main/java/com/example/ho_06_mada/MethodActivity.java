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
import android.widget.TextView;
import android.widget.Toast;

public class MethodActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton1,radioButton2,radioButton3;
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
        setContentView(R.layout.activity_method);

        Switch soundSw = (Switch) findViewById(R.id.switch5);
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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("startOption")) {
            String startOption = intent.getStringExtra("startOption").toString();
            RadioButton method1 = findViewById(R.id.radioButton1);
            RadioButton method2 = findViewById(R.id.radioButton2);
            RadioButton method3 = findViewById(R.id.radioButton3);
            if (startOption.equals("1")) {
                method1.setText("Long distance running (not less than 30 mins)");
                method2.setText("Swimming (not less than 30 mins)");
                method3.setText("Cycling (not less than 30 mins)");
            }
            else if (startOption.equals("2")) {
                method1.setText("Push-up (Develop muscular endurance)");
                method2.setText("Weightlifting (Develop maximum muscle strength)");
                method3.setText("Leg press machine" +
                        "(Develop maximum muscle strength/endurance)");
            }
            else if (startOption.equals("3")) {
                method1.setText("Solo stretching exercises");
                method2.setText("Yoga");
                method3.setText("Towel Exercise (More suitable for the elderly)");
            }
            else if (startOption.equals("4")) {
                method1.setText("35 minutes of standing training (Exercise intensity: Beginner)");
                method2.setText("Interval exercise training (Exercise intensity: Medium)");
                method3.setText("20 min full body workout (Exercise intensity: Medium to high level)");
            }
            else if (startOption.equals("5")) {
                method1.setText("Interval leg training (3 times/week)");
                method2.setText("Circuit training (Perform 3-5 cycles)");
                method3.setText("Resistance training (85-95%/1RM)");
            }

            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
            radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
            radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
            next = (Button) findViewById(R.id.button7);
            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MethodActivity.this, TrainingActivity.class);
                    intent.putExtra("startOption", startOption);
                    if (radioButton1.isChecked()) {
                        intent.putExtra("methodOption", "1");
                    } else if (radioButton2.isChecked()) {
                        intent.putExtra("methodOption", "2");
                    } else if (radioButton3.isChecked()) {
                        intent.putExtra("methodOption", "3");
                    }
                    startActivity(intent);
                }
            });
        }
    }
}