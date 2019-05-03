package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.vantrantrucphuong.quanlyhocphi.R;

public class MainActivity extends AppCompatActivity {

    TextView txtStudentList, txtSubjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        txtStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentList.class);
                startActivity(intent);
            }
        });

        txtSubjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SubjectList.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        txtStudentList = (TextView) findViewById(R.id.txtStudentList);
        txtSubjectList = (TextView) findViewById(R.id.txtSubjectList);
    }
}
