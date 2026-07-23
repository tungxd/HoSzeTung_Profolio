package com.example.ho_06_mada;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
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
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "user");
        EditText editMessage = findViewById(R.id.edit_message);
        editMessage.setText(username);

        editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String updatedName = editable.toString().trim();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", updatedName);
                editor.apply();
            }
        });

        Switch soundSw = (Switch) findViewById(R.id.swSound);
        soundSw.setChecked(true);
        soundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (myMus != null) myMus.start();
                } else {
                    if (myMus != null) myMus.pause();
                }
            }
        });
        myMus = MediaPlayer.create(this, R.raw.bgmusic);
        myMus.setLooping(true);
    }
}