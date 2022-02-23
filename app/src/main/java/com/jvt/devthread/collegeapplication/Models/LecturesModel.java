package com.jvt.devthread.collegeapplication.Models;

public class LecturesModel {
    String time, roomNumber, courseCode, section, lectureId;

    public LecturesModel() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public LecturesModel(String time, String roomNumber, String courseCode, String section, String lectureId) {
        this.time = time;
        this.roomNumber = roomNumber;
        this.courseCode = courseCode;
        this.section = section;
        this.lectureId = lectureId;
    }
}
