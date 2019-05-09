package com.example.vantrantrucphuong.quanlyhocphi.Model;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */

public class Invoice {
    private int id;
    private String invoice_id;
    private String invoice_date;
    private String invoice_student;


    public Invoice() {
    }

    public Invoice(int id, String invoice_id, String invoice_date, String invoice_student) {
        this.id = id;
        this.invoice_id = invoice_id;
        this.invoice_date = invoice_date;
        this.invoice_student = invoice_student;
    }

    public Invoice(String invoice_id, String invoice_date, String invoice_student) {
        this.invoice_id = invoice_id;
        this.invoice_date = invoice_date;
        this.invoice_student = invoice_student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getInvoice_student() {
        return invoice_student;
    }

    public void setInvoice_student(String invoice_student) {
        this.invoice_student = invoice_student;
    }
}
