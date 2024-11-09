package com.example.quicknotes;

public class Note {

    private int id;
    private String title;
    private String preview;
    private String category;

    public Note(int id, String title, String preview, String category) {
        this.id = id;
        this.title = title;
        this.preview = preview;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPreview() {
        return preview;
    }

    public String getCategory() {
        return category;
    }
}
