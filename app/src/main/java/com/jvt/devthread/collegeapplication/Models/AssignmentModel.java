package com.jvt.devthread.collegeapplication.Models;

public class AssignmentModel {
    String subject, assignmentType, uploadedTime, deadline, teacherName, assignmentId, heading, content;

    public AssignmentModel() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(String uploadedTime) {
        this.uploadedTime = uploadedTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
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

    public AssignmentModel(String subject, String assignmentType, String uploadedTime, String deadline, String teacherName, String assignmentId, String heading, String content) {
        this.subject = subject;
        this.assignmentType = assignmentType;
        this.uploadedTime = uploadedTime;
        this.deadline = deadline;
        this.teacherName = teacherName;
        this.assignmentId = assignmentId;
        this.heading = heading;
        this.content = content;
    }
}
