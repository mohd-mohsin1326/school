package com.example.school.model;

public class Student {
    // user data
    private int studentId;
    private String studentName;
    private String gender;
    private int standard;

    // constructor
    public Student(int studentId, String studentName, String gender, int standard) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.standard = standard;
    }

    // getters setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

}
