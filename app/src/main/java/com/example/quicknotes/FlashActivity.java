package com.example.quicknotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        // Handler to delay the transition to HomeActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(FlashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close FlashActivity to prevent user from returning to it
        }, SPLASH_TIME_OUT);
    }
}
