package com.jvt.devthread.collegeapplication.Models;

public class HappeningsModel {
    String image, id, heading, content, time;

    public HappeningsModel() {
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

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public HappeningsModel(String image, String id, String heading, String content, String time) {
        this.image = image;
        this.id = id;
        this.heading = heading;
        this.content = content;
        this.time = time;
    }
}
