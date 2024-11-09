package com.example.quicknotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<String> noteTitles;
    private List<String> notePreviews;

    public NoteAdapter(List<String> noteTitles, List<String> notePreviews) {
        this.noteTitles = noteTitles;
        this.notePreviews = notePreviews;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.titleTextView.setText(noteTitles.get(position));
        holder.previewTextView.setText(notePreviews.get(position));
    }

    @Override
    public int getItemCount() {
        return noteTitles.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView previewTextView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.noteTitle);
            previewTextView = itemView.findViewById(R.id.notePreview);
        }
    }
}
