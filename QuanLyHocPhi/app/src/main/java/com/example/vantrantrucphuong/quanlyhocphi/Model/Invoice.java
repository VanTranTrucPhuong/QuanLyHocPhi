package com.example.vantrantrucphuong.quanlyhocphi.Model;

/**
 * Created by Tran Thanh Loc on 5/1/2019.
 */

public class Invoice {
    private String invoiceNumber;
    private String date;
    private String sudent_id;

    public Invoice(String invoiceNumber, String date, String sudent_id) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.sudent_id = sudent_id;
    }

    public Invoice() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSudent_id() {
        return sudent_id;
    }

    public void setSudent_id(String sudent_id) {
        this.sudent_id = sudent_id;
    }
}
