package com.example.ho_06_mada;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private GestureDetectorCompat gDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        showToast("onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        showToast("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        showToast("onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float dX, float dY) {
        showToast("onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        showToast("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        showToast("Fling Gesture Detected.\n" +
                "START X: " + e1.getX() + ", Y: " + e1.getY() + "\n" +
                "END X: " + e2.getX() + ", Y: " + e2.getY() + "\n" +
                "Velocity X: " + velocityX + ", Y: " + velocityY);
        return false;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

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
    public void toStart(View view){
        startActivity(new Intent(this,StartActivity.class));
    }
    public void toStory(View view){
        startActivity(new Intent(this,StoryActivity.class));
    }
    public void toSetting(View view){
        startActivity(new Intent(this,SettingActivity.class));
    }
    public void toUs(View view){
        startActivity(new Intent(this,UsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gDetector = new GestureDetectorCompat(this, this);

        ImageView imageView = findViewById(R.id.buddyhi_imgView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animSet = new AnimationSet(true);

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1.0f,
                        1.2f,
                        1.0f,
                        1.2f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);
                scaleAnimation.setDuration(200);
                animSet.addAnimation(scaleAnimation);

                RotateAnimation rotateAnimation = new RotateAnimation(
                        0.0f,
                        360.0f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);
                rotateAnimation.setDuration(200);
                animSet.addAnimation(rotateAnimation);

                view.startAnimation(animSet);
            }
        });

        ImageView imageView2 = findViewById(R.id.lung_imgView);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animSet = new AnimationSet(true);

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1.0f,
                        1.2f,
                        1.0f,
                        1.2f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);
                scaleAnimation.setDuration(200);
                animSet.addAnimation(scaleAnimation);

                RotateAnimation rotateAnimation = new RotateAnimation(
                        0.0f,
                        360.0f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);
                rotateAnimation.setDuration(200);
                animSet.addAnimation(rotateAnimation);

                view.startAnimation(animSet);
            }
        });

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "user");

        if (username != null) {
            String sentence = "Welcome, "+ username + "!";
            TextView textView = findViewById(R.id.textView2);
            textView.setText(sentence);
        }
        Switch soundSw = (Switch) findViewById(R.id.switch1);
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