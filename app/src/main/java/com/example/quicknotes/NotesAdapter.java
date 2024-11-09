package com.example.quicknotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notesList;
    private Context context;

    // Constructor to initialize the adapter with the notes list and context
    public NotesAdapter(List<Note> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout (make sure you have a layout for each note item)
        View itemView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        // Get the note at the current position
        Note note = notesList.get(position);

        // Bind the note data to the view elements
        holder.noteTitle.setText(note.getTitle());
        holder.notePreview.setText(note.getPreview());
        holder.noteCategory.setText(note.getCategory());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    // Method to update the list of notes (for filtering purposes)
    public void updateNotes(List<Note> filteredNotes) {
        this.notesList = filteredNotes;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold the views for each item
    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, notePreview, noteCategory;

        public NoteViewHolder(View itemView) {
            super(itemView);
            // Initialize the views in your item layout
            noteTitle = itemView.findViewById(R.id.noteTitle); // Note title TextView
            notePreview = itemView.findViewById(R.id.notePreview); // Note preview TextView
            noteCategory = itemView.findViewById(R.id.noteCategory); // Category TextView
        }
    }
}
