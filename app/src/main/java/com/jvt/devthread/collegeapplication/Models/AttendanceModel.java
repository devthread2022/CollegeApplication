package com.jvt.devthread.collegeapplication.Models;

public class AttendanceModel {
    String courseName, sectionName, rollNumber, faculty, facultySeating, attended, delivered, attendanceId;

    public AttendanceModel() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFacultySeating() {
        return facultySeating;
    }

    public void setFacultySeating(String facultySeating) {
        this.facultySeating = facultySeating;
    }

    public String getAttended() {
        return attended;
    }

    public void setAttended(String attended) {
        this.attended = attended;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public AttendanceModel(String courseName, String sectionName, String rollNumber, String faculty, String facultySeating, String attended, String delivered, String attendanceId) {
        this.courseName = courseName;
        this.sectionName = sectionName;
        this.rollNumber = rollNumber;
        this.faculty = faculty;
        this.facultySeating = facultySeating;
        this.attended = attended;
        this.delivered = delivered;
        this.attendanceId = attendanceId;
    }
}
