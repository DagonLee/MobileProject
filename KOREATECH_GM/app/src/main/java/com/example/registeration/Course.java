package com.example.registeration;

public class Course {
    int courseID;//강의 고유 번호
    String courseCode;//
    int courseYear;//해당 연도
    String courseTerm;
    String courseTitle;
    int courseCredit;
    String courseMajor;
    String courseArea;

    //public Course(String courseCode, String courseTitle, int courseCredit, String courseMajor, String courseArea){
    public Course(int courseID, String courseCode, int courseYear, String courseTerm, String courseTitle, int courseCredit, String courseMajor, String courseArea) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseYear = courseYear;
        this.courseTerm = courseTerm;//
        this.courseTitle = courseTitle;//강의 제목
        this.courseCredit = courseCredit;//강의 학점
        this.courseMajor = courseMajor;//강의 전공
        this.courseArea = courseArea;//강의 영역
    }


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

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
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

    public String getCourseMajor() {
        return courseMajor;
    }

    public void setCourseMajor(String courseMajor) {
        this.courseMajor = courseMajor;
    }

    public String getCourseArea() {
        return courseArea;
    }

    public void setCourseArea(String courseArea) {
        this.courseArea = courseArea;
    }




}
