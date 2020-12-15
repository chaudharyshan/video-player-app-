package com.player.player;

public class VideoFile {
    private String id;
    private String path;
    private String title;
    private String fileName;
    private String size;
    private String dateAdded;
    private String duration;

    public VideoFile(String id, String path, String title, String fileName, String size, String dateAdded, String duration) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.fileName = fileName;
        this.size = size;
        this.dateAdded = dateAdded;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSize() {
        return size;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getDuration() {
        return duration;
    }
}
