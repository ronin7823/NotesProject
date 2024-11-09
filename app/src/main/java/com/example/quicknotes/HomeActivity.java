package com.example.quicknotes;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;
    private FloatingActionButton fabAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RecyclerView and FAB
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        fabAddNote = findViewById(R.id.fabAddNote);

        // Set up RecyclerView
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // You need to implement a custom adapter for the RecyclerView (we'll do that next)

        // Set up the FAB to open the Note Creation screen
        fabAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NoteCreationActivity.class);
            startActivity(intent);
        });
    }
}
