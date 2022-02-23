package com.jvt.devthread.collegeapplication.Models;

public class ExamModel {
    String examId, courseName, courseCode, date, time, roomNumber;

    public ExamModel() {
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public ExamModel(String examId, String courseName, String courseCode, String date, String time, String roomNumber) {
        this.examId = examId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.date = date;
        this.time = time;
        this.roomNumber = roomNumber;
    }
}
