package com.example.quicknotes;

public class Note {

    private String title;
    private String preview;
    private String category;

    // Constructor to initialize Note object
    public Note(String title, String preview, String category) {
        this.title = title;
        this.preview = preview;
        this.category = category;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for preview
    public String getPreview() {
        return preview;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Optional: Setter methods if you want to update values later
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
