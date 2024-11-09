package com.example.quicknotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private Context context;
    private ArrayList<Note> notes;

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());  // Set note title
        holder.previewTextView.setText(note.getPreview());  // Set note preview
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateNotes(ArrayList<Note> updatedNotes) {
        this.notes = updatedNotes;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;  // ID for note title TextView
        TextView previewTextView;  // ID for note preview TextView

        NoteViewHolder(View itemView) {
            super(itemView);
            // Find views by ID from the item layout
            titleTextView = itemView.findViewById(R.id.titleTextView);
            previewTextView = itemView.findViewById(R.id.previewTextView);
        }
    }
}
