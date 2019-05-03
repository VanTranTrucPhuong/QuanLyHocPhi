package com.example.vantrantrucphuong.quanlyhocphi.Model;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */

public class Subject {
    private String subject_id;
    private String subjectName;
    private String creditNumber;

    public Subject(String subject_id, String subjectName, String creditNumber) {
        this.subject_id = subject_id;
        this.subjectName = subjectName;
        this.creditNumber = creditNumber;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }
}
