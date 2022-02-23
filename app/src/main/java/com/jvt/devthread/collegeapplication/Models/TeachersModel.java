package com.jvt.devthread.collegeapplication.Models;

public class TeachersModel {
    String teacherId, teacherName, seatingRoom, specialization,availability;

    public TeachersModel() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSeatingRoom() {
        return seatingRoom;
    }

    public void setSeatingRoom(String seatingRoom) {
        this.seatingRoom = seatingRoom;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public TeachersModel(String teacherId, String teacherName, String seatingRoom, String specialization, String availability) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.seatingRoom = seatingRoom;
        this.specialization = specialization;
        this.availability = availability;
    }
}
