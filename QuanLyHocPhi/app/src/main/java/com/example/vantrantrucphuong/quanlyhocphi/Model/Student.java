package com.example.vantrantrucphuong.quanlyhocphi.Model;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */

public class Student {
    private String student_id;
    private String name;
    private String phoneNumber;

    public Student(String student_id, String name, String phoneNumber) {
        this.student_id = student_id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
