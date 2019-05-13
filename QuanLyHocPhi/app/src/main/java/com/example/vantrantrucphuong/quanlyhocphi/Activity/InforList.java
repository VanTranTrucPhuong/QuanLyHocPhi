package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vantrantrucphuong.quanlyhocphi.Adapter.InforAdapter;
import com.example.vantrantrucphuong.quanlyhocphi.Database.InforModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Infor;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.List;


public class InforList extends AppCompatActivity {

    InforModify inforModify; //thong tin hoa don
    private ListView lvInfor;
    private InforAdapter customAdapter;
    private List<Infor> inforList;
    public String masoHoaDon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvInfor = (ListView) findViewById(R.id.listViewInfor);

        Intent intent = getIntent();
        masoHoaDon= intent.getStringExtra(InvoiceList.masoHoaDon);
        inforModify = new InforModify(this);
        inforList = inforModify.getSubonInfor(masoHoaDon);

        setAdapter();
        registerForContextMenu(lvInfor);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new InforAdapter(this, R.layout.item_list_infor, inforList);
            lvInfor.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvInfor.setSelection(customAdapter.getCount()-1);
        }
    }
    public void updateListInfor(){
        inforList.clear();
        inforList.addAll(inforModify.getSubonInfor(masoHoaDon));
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_invoice,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Infor inforItem = (Infor) customAdapter.getItem(info.position);
        final String id = inforItem.getInvoice_id();
        final String maMh = inforItem.getSub_id();

//        Toast.makeText(this, (lvInfor.getItemAtPosition(info.position)).toString(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {


            case R.id.action_delete:
//                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                int result = inforModify.delete(maMh, id);

                if (result > 0) {
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    updateListInfor();
                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                return true;


        }

        return super.onContextItemSelected(item);
    }





}
