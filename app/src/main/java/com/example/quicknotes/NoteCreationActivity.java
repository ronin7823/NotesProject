package com.example.quicknotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NoteCreationActivity extends AppCompatActivity {

    private EditText editTextNoteTitle, editTextNotePreview;
    private Spinner spinnerCategory;
    private Button buttonSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creation);

        // Initialize the views
        editTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        editTextNotePreview = findViewById(R.id.editTextNotePreview);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);

        // Set up the Spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.note_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // Set up the "Save Note" button
        buttonSaveNote.setOnClickListener(v -> {
            // Get data from the input fields
            String noteTitle = editTextNoteTitle.getText().toString();
            String notePreview = editTextNotePreview.getText().toString();
            String noteCategory = spinnerCategory.getSelectedItem().toString(); // Get selected category

            // Validate input
            if (noteTitle.isEmpty() || notePreview.isEmpty()) {
                Toast.makeText(NoteCreationActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save the note
                saveNote(noteTitle, notePreview, noteCategory);
            }
        });
    }

    // Method to save the note
    private void saveNote(String title, String preview, String category) {
        // Create an Intent to pass data back to the HomeActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("noteTitle", title);
        resultIntent.putExtra("notePreview", preview);
        resultIntent.putExtra("noteCategory", category);

        // Set the result to indicate that the note is saved
        setResult(RESULT_OK, resultIntent);

        // Show a message confirming that the note is saved
        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();

        // Finish the activity and return to the home screen
        finish();
    }
}
