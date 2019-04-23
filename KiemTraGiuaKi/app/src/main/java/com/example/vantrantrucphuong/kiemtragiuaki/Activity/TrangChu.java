package com.example.vantrantrucphuong.kiemtragiuaki.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vantrantrucphuong.kiemtragiuaki.Adapter.ListViewAdapterSV;
import com.example.vantrantrucphuong.kiemtragiuaki.Model.CustomListViewSV;
import com.example.vantrantrucphuong.kiemtragiuaki.R;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {
    ListView listView;
    ArrayList<CustomListViewSV> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        setControl();
        setEvent();
    }

    private void setControl(){
        listView = (ListView) findViewById(R.id.listView);
    }
    private void setEvent() {
        khoiTao();
        ListViewAdapterSV listViewAdapter = new ListViewAdapterSV(this,R.layout.row_listview_sv, data);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), BienLaiActivity.class);
                startActivity(intent);
            }
        });
    }
    private void khoiTao() {
        data.add(new CustomListViewSV(R.drawable.student,"Trần Nhật Tường", "N15DCCN089" , "0909701777"));
        data.add(new CustomListViewSV(R.drawable.student,"Văn Trần Trúc Phương", "N15DCCN089","0909701777"));
        data.add(new CustomListViewSV(R.drawable.student,"Trần Thanh Lộc", "N15DCCN089","0909701777"));
        data.add(new CustomListViewSV(R.drawable.student,"Nguyễn Thị Thanh Thảo", "N15DCCN089","0909701777"));
        data.add(new CustomListViewSV(R.drawable.student,"Ðinh Hoài Hiệp", "N15DCCN089","0909701777"));
    }
}
