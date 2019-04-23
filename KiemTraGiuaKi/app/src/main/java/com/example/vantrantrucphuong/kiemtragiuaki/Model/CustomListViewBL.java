package com.example.vantrantrucphuong.kiemtragiuaki.Model;

/**
 * Created by Van Tran Truc Phuong on 4/20/2019.
 */

public class CustomListViewBL {
    private String bill_number;
    private String bill_date;

    public CustomListViewBL(String bill_number, String bill_date) {
        this.bill_number = bill_number;
        this.bill_date = bill_date;
    }

    public String getBill_number() {
        return bill_number;
    }

    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }
}
