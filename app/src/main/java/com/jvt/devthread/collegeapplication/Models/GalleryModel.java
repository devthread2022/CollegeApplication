package com.jvt.devthread.collegeapplication.Models;

public class GalleryModel {
    String image, id, note;

    public GalleryModel() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GalleryModel(String image, String id, String note) {
        this.image = image;
        this.id = id;
        this.note = note;
    }
}
