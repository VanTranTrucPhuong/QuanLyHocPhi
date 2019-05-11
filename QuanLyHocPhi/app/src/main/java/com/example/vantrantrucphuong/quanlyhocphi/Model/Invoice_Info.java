package com.example.vantrantrucphuong.quanlyhocphi.Model;

public class Invoice_Info {
    private String invoice_id;
    private String subject_id;
    private String cost;
    public Invoice_Info() {
    }

    public Invoice_Info(String invoice_id, String subject_id, String cost) {
        this.invoice_id = invoice_id;
        this.subject_id = subject_id;
        this.cost = cost;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
