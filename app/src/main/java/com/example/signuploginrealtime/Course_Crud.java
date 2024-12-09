package com.example.signuploginrealtime;

public class Course_Crud {
    private int id;
    private String title;
    private String category;
    private String description;
    private String image;
    private String youtubeUrl;

    public Course_Crud(int id, String title, String category, String description, String image, String youtubeUrl) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.image = image;
        this.youtubeUrl = youtubeUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}
