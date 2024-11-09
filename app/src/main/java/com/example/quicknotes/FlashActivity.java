package com.example.quicknotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        // Delay for 2 seconds before moving to MainActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(FlashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the flash screen so it's not on the back stack
        }, 2000); // 2000 ms delay
    }
}
