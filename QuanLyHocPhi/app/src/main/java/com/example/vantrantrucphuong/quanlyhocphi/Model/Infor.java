package com.example.vantrantrucphuong.quanlyhocphi.Model;

/**
 * Created by Van Tran Truc Phuong on 5/11/2019.
 */

public class Infor {
    private String sub_id;
    private String invoice_id;
    private String money;
    public Infor() {
    }

    public Infor(String invoice_id, String sub_id, String money) {
        this.invoice_id = invoice_id;
        this.sub_id = sub_id;
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }
}
