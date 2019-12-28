package com.example.registeration;

public class DetailCourse {
    int courseID;
    String courseCode;
    String courseArea;
    String courseTitle;
    int courseCredit;
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseArea() {
        return courseArea;
    }

    public void setCourseArea(String courseArea) {
        this.courseArea = courseArea;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public DetailCourse(int courseID, String courseCode, String courseArea, String courseTitle, int courseCredit) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseArea = courseArea;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
    }
}
