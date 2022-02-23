package com.jvt.devthread.collegeapplication.Models;

public class RmsModel {
    String rmsId, date, department, category, content,status;

    public RmsModel() {
    }

    public String getRmsId() {
        return rmsId;
    }

    public void setRmsId(String rmsId) {
        this.rmsId = rmsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RmsModel(String rmsId, String date, String department, String category, String content, String status) {
        this.rmsId = rmsId;
        this.date = date;
        this.department = department;
        this.category = category;
        this.content = content;
        this.status = status;
    }
}
