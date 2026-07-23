package com.example.ho_06_mada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class TrainingActivity extends AppCompatActivity {
    private String videoUrl;
    public void onClick(View view) {
        startActivity(new Intent(this,FinishActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        WebView webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("startOption") && intent.hasExtra("methodOption")) {
            String startOption = intent.getStringExtra("startOption");
            String methodOption = intent.getStringExtra("methodOption");
            TextView method = findViewById(R.id.textView10);
            TextView name = findViewById(R.id.textView13);

            if (startOption.equals("1") && methodOption.equals("1")) {
                method.setText("You selected method 1");
                name.setText("Long distance running (not less than 30 mins)\n"+
                        "Plan: ~20-30% 10KM PACE or ~10-20% MARATHON PACE");
                videoUrl = "https://www.youtube.com/embed/ZoDnJU6kjCE";
            }
            else if (startOption.equals("1") && methodOption.equals("2")) {
                method.setText("You selected method 2");
                name.setText("Swimming (not less than 30 mins)\n"+
                        "Plan: 1. Warm up 2. Pre-set 3. Main set 4. Post-set 5. Cool down");
                videoUrl = "https://www.youtube.com/embed/oM4sHl1hTEE";
            }
            else if (startOption.equals("1") && methodOption.equals("3")) {
                method.setText("You selected method 3");
                name.setText("Cycling (not less than 30 mins)\n"+
                        "Plan: 1. Time 2. Goals 3. Specificity 4. Regularity " +
                        "5. Testing 6. Progression 7. Rest");
                videoUrl = "https://www.youtube.com/embed/il5vPB3o1WM";
            }

            else if (startOption.equals("2") && methodOption.equals("1")) {
                method.setText("You selected method 1");
                name.setText("Push-up (Develop muscular endurance)\n"+
                        "Pay attention to: 1. Body position 2. Arm position " +
                        "3. Range of motion 4. Shoulder blade movement");
                videoUrl = "https://www.youtube.com/embed/IODxDxX7oi4";
            }
            else if (startOption.equals("2") && methodOption.equals("2")) {
                method.setText("You selected method 2");
                name.setText("Weightlifting (Develop maximum muscle strength)\n"+
                        "- Powerlastic exercise bands\n"+"- Floor to the knee\n"+
                        "and more exercises...");
                videoUrl = "https://www.youtube.com/embed/Uh3UmA0lgIU";
            }
            else if (startOption.equals("2") && methodOption.equals("3")) {
                method.setText("You selected method 3");
                name.setText("Leg press machine \n" +
                        "(Develop maximum muscle strength/endurance)");
                videoUrl = "https://www.youtube.com/embed/M_xoJ0iRLFc";
            }

            else if (startOption.equals("3") && methodOption.equals("1")) {
                method.setText("You selected method 1");
                name.setText("Solo stretching exercises\n"+"- 7 min Exercise\n"
                        +"- You can cool down your full body properly");
                videoUrl = "https://www.youtube.com/embed/FlAvRcz0zuY";
            }
            else if (startOption.equals("3") && methodOption.equals("2")) {
                method.setText("You selected method 2");
                name.setText("Yoga\n"+"- 22 Minute Full Body Gentle Yoga\n"+
                        "- Ideal for athletes or Beginners and easy days to recover, stretch and strengthen");
                videoUrl = "https://www.youtube.com/embed/B4kNiCWTl7M";
            }
            else if (startOption.equals("3") && methodOption.equals("3")) {
                method.setText("You selected method 3");
                name.setText("Towel Exercise (More suitable for the elderly)\n"+
                        "- 10 Minutes\n"+"- Target the muscles in your arms, shoulders, and back");
                videoUrl = "https://www.youtube.com/embed/BNtpr9zhm8A";
            }

            else if (startOption.equals("4") && methodOption.equals("1")) {
                method.setText("You selected method 1");
                name.setText("35 minutes of standing training (Exercise intensity: Beginner)\n"+
                        "- Suitable for beginners\n"+"- No jumping action\n"+"- There is warm-up and stretching");
                videoUrl = "https://www.youtube.com/embed/Wx5pgfStY3w";
            }
            else if (startOption.equals("4") && methodOption.equals("2")) {
                method.setText("You selected method 2");
                name.setText("Interval exercise training (Exercise intensity: Medium)\n"+"- 16 exercises");
                videoUrl = "https://www.youtube.com/embed/q3ySMqHWcSU";
            }
            else if (startOption.equals("4") && methodOption.equals("3")) {
                method.setText("You selected method 3");
                name.setText("20 min full body workout (Exercise intensity: Medium to high level)\n"+
                        "- No equipment\n"+"- Intense Routine");
                videoUrl = "https://www.youtube.com/embed/bTo4NrSriWw";
            }

            else if (startOption.equals("5") && methodOption.equals("1")) {
                method.setText("You selected method 1");
                name.setText("Interval leg training (3 times/week)\n"+"- 14 exercises\n"+
                        "- 15 minutes\n"+"- With combining isometrics and plyometrics");
                videoUrl = "https://www.youtube.com/embed/KNSwNKt65Vk";
            }
            else if (startOption.equals("5") && methodOption.equals("2")) {
                method.setText("You selected method 2");
                name.setText("Circuit training (Perform 3-5 cycles)\n"+
                        "- 9 steps for 1 cycle\n"+"- Try to always keep the exercises changing weekly");
                videoUrl = "https://www.youtube.com/embed/kTJ5b0RkKX8";
            }
            else if (startOption.equals("5") && methodOption.equals("3")) {
                method.setText("You selected method 3");
                name.setText("Resistance training (85-95%/1RM)\n"+"- 30 minutes\n"+
                        "- Recommand user uses a pair of dumbbel");
                videoUrl = "https://www.youtube.com/embed/t3kL5gswXAc";
            }

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(videoUrl);

        }
    }
}