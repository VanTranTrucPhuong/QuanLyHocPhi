package com.example.vantrantrucphuong.quanlyhocphi.Model;

public class Invoice {
    private String invoice_id;
    private String invoice_date;
    private String invoice_student;
    public Invoice() {
    }

    public Invoice(String invoice_id, String invoice_date, String invoice_student) {
        this.invoice_id = invoice_id;
        this.invoice_date = invoice_date;
        this.invoice_student = invoice_student;
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
