package com.example.quicknotes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;


public class HomeActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;
    private FloatingActionButton fabAddNote;
    private NotesAdapter notesAdapter;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RecyclerView and FAB
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        fabAddNote = findViewById(R.id.fabAddNote);

        // Initialize notes list and adapter
        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList, this);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setAdapter(notesAdapter);

        // Add sample notes (In real app, you would load these from a database or other storage)
        addSampleNotes();

        // Set up the FAB to open the Note Creation screen
        fabAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NoteCreationActivity.class);
            startActivity(intent);
        });
    }

    // Method to filter notes by category
    public void filterNotesByCategory(String category) {
        List<Note> filteredNotes = new ArrayList<>();
        for (Note note : notesList) {
            if (note.getCategory().equals(category)) {
                filteredNotes.add(note);
            }
        }
        notesAdapter.updateNotes(filteredNotes);
    }

    // Sample notes for testing (Replace with actual saved notes)
    private void addSampleNotes() {
        notesList.add(new Note("Work Task", "Complete project documentation", "Work"));
        notesList.add(new Note("Personal Goal", "Exercise for 30 minutes", "Personal"));
        notesList.add(new Note("Idea", "Start a new blog", "Ideas"));
    }
}
