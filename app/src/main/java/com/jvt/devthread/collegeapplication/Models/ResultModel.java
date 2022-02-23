package com.jvt.devthread.collegeapplication.Models;

public class ResultModel {
    String id, grade, course;

    public ResultModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ResultModel(String id, String grade, String course) {
        this.id = id;
        this.grade = grade;
        this.course = course;
    }
}
