package com.example.vantrantrucphuong.kiemtragiuaki.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vantrantrucphuong.kiemtragiuaki.R;

public class ChiTietBienLaiActivity extends AppCompatActivity {

    EditText edtSoBL, edtNgayHP, edtMaMonHoc, edtTenMonHoc, edtSoTC, edtSoTien;
    Button btn_TroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bien_lai);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btn_TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setControl() {
        edtSoBL = (EditText) findViewById(R.id.edtSoBL);
        edtNgayHP = (EditText) findViewById(R.id.edtNgayHP);
        edtMaMonHoc = (EditText) findViewById(R.id.edtMaMonHoc);
        edtTenMonHoc = (EditText) findViewById(R.id.edtTenMonHoc);
        edtSoTC = (EditText) findViewById(R.id.edtSoTinChi);
        edtSoTien = (EditText) findViewById(R.id.edtSoTien);
        btn_TroVe = (Button) findViewById(R.id.btn_TroVe);
    }
}
