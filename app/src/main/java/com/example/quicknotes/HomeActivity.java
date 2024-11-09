package com.example.quicknotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private ArrayList<Note> notes;
    private NoteDatabaseHelper noteDatabaseHelper;
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize Spinner
        categorySpinner = findViewById(R.id.categorySpinner);

        noteDatabaseHelper = new NoteDatabaseHelper(this);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notes = noteDatabaseHelper.getAllNotes();
        notesAdapter = new NotesAdapter(this, notes);
        recyclerView.setAdapter(notesAdapter);

        // Set up category spinner
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.note_categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Filter notes by category
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = parentView.getItemAtPosition(position).toString();
                filterNotesByCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                filterNotesByCategory(null);
            }
        });

        // Floating Action Button (FAB) for creating a new note
        FloatingActionButton fab = findViewById(R.id.fabAddNote);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NoteCreationActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void filterNotesByCategory(String category) {
        ArrayList<Note> filteredNotes = new ArrayList<>();
        for (Note note : notes) {
            if (category == null || note.getCategory().equals(category)) {
                filteredNotes.add(note);
            }
        }
        notesAdapter.updateNotes(filteredNotes);  // Update with filtered notes
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String noteTitle = data.getStringExtra("noteTitle");
            String notePreview = data.getStringExtra("notePreview");
            String noteCategory = data.getStringExtra("noteCategory");

            // Save the new note to SQLite
            noteDatabaseHelper.saveNote(noteTitle, notePreview, noteCategory);

            // Refresh the notes list
            notes = noteDatabaseHelper.getAllNotes();
            notesAdapter.updateNotes(notes);  // Update the adapter with the new list

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
