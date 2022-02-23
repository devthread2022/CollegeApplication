package com.jvt.devthread.collegeapplication.Models;

public class AnnouncementModel {
    String announcementId, department, date, heading, content, uploadedBy;

    public AnnouncementModel() {
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public AnnouncementModel(String announcementId, String department, String date, String heading, String content, String uploadedBy) {
        this.announcementId = announcementId;
        this.department = department;
        this.date = date;
        this.heading = heading;
        this.content = content;
        this.uploadedBy = uploadedBy;
    }
}
