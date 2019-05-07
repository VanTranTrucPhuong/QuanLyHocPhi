package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vantrantrucphuong.quanlyhocphi.Adapter.InvoiceAdapter;
import com.example.vantrantrucphuong.quanlyhocphi.Adapter.StudentAdapter;
import com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper;
import com.example.vantrantrucphuong.quanlyhocphi.Database.InvoiceModify;
import com.example.vantrantrucphuong.quanlyhocphi.Database.StudentModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.ArrayList;
import java.util.List;

public class InvoiceList extends AppCompatActivity {
    InvoiceModify invoiceModify;
    InvoiceAdapter adapter;
    ListView lvHD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvHD=(ListView)findViewById(R.id.listView);
        invoiceModify = new InvoiceModify(this);
        display();
        registerForContextMenu(lvHD);
    }

    public void display(){
        ArrayList<Invoice> data = invoiceModify.getAllInvoice();
        adapter =  new InvoiceAdapter(this, R.layout.item_list_invoice , data);
        lvHD.setAdapter(adapter);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //add item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {
            final Dialog dialog =new Dialog(this);
            dialog.setTitle("Thêm hóa đơn mới ");
            dialog.setContentView(R.layout.dialog_add_invoice);
            final EditText edtMahoadon, edtNgay, edtMaSV;
            Button btnCancel, btnInsert;

            edtMahoadon=(EditText) dialog.findViewById(R.id.edtMaHoadon);
            edtNgay=(EditText) dialog.findViewById(R.id.edtNgay);
            edtMaSV=(EditText) dialog.findViewById(R.id.edtMaSV);

            btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
            btnInsert=(Button) dialog.findViewById(R.id.btnUpdate);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Invoice invoice=new Invoice(edtMahoadon.getText().toString(),edtNgay.getText().toString(), edtMaSV.getText().toString());
                    Toast.makeText(InvoiceList.this, invoice.getInvoiceNumber(), Toast.LENGTH_SHORT).show();
                    InvoiceModify.insert(invoice);
                    display();
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
