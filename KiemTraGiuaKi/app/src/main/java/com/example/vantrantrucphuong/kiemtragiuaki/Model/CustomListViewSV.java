package com.example.vantrantrucphuong.kiemtragiuaki.Model;

/**
 * Created by Van Tran Truc Phuong on 4/20/2019.
 */

public class CustomListViewSV {

    private int imageId;
    private String title;
    private String text;
    private String sdt;

    public CustomListViewSV(int imageId, String title, String text , String sdt) {
        this.imageId = imageId;
        this.title = title;
        this.text = text;
        this.sdt = sdt;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getSdt() {
        return sdt;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
