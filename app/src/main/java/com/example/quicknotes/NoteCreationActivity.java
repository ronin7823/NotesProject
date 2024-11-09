package com.example.quicknotes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class NoteCreationActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextPreview;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creation);

        // Initialize UI components
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextPreview = findViewById(R.id.editTextPreview);
        buttonSave = findViewById(R.id.buttonSave);

        // Set up save button click listener
        buttonSave.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String preview = editTextPreview.getText().toString();

            if (title.isEmpty() || preview.isEmpty()) {
                Toast.makeText(this, "Please enter both title and preview", Toast.LENGTH_SHORT).show();
            } else {
                // Logic to save the note, for now, just a simple toast
                Toast.makeText(this, "Note saved: " + title, Toast.LENGTH_SHORT).show();
                // Return to the home activity (if necessary)
                finish(); // This will close this activity and return to the previous one
            }
        });
    }
}
