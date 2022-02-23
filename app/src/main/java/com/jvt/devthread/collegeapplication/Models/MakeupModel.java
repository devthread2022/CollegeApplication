package com.jvt.devthread.collegeapplication.Models;

public class MakeupModel {
    String id, time, takenBy, courseCode;

    public MakeupModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public MakeupModel(String id, String time, String takenBy, String courseCode) {
        this.id = id;
        this.time = time;
        this.takenBy = takenBy;
        this.courseCode = courseCode;
    }
}
