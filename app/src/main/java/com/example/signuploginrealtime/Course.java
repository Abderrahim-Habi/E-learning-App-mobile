package com.example.signuploginrealtime;

public class Course {
    private String title;
    private String subtitle;
    private String imageUrl;

    public Course(String title, String subtitle, String imageUrl) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
