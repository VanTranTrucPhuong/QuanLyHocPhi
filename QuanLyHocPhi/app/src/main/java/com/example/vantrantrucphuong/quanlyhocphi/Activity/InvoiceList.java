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
import com.example.vantrantrucphuong.quanlyhocphi.Database.InvoiceModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.ArrayList;

public class InvoiceList extends AppCompatActivity {
    InvoiceModify invoiceModify;
    InvoiceAdapter invoiceAdapter;
    ListView lvHD;
    private ArrayList<Invoice> invoiceList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvHD=(ListView)findViewById(R.id.listView);
        invoiceModify = new InvoiceModify(this);
        invoiceList = invoiceModify.getAllInvoice();
        setAdapter();
        registerForContextMenu(lvHD);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setAdapter() {
        if (invoiceAdapter == null) {
            invoiceAdapter = new InvoiceAdapter(this, R.layout.item_list_invoice, invoiceList);
            lvHD.setAdapter(invoiceAdapter);
        }else{
            invoiceAdapter.notifyDataSetChanged();
            lvHD.setSelection(invoiceAdapter.getCount()-1);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    //add item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new) {
            final Dialog dialog=new Dialog(this);
            dialog.setTitle("Thêm mới hóa đơn");
            dialog.setContentView(R.layout.dialog_add_invoice);
            final EditText edtInvoiceNumber, edtDate, edtStudentID;
            Button btnCancel, btnInsert;

            edtInvoiceNumber=(EditText) dialog.findViewById(R.id.edtMaHoadon);
            edtDate=(EditText) dialog.findViewById(R.id.edtNgay);
            edtStudentID=(EditText) dialog.findViewById(R.id.edtMaSV);

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
                    Invoice invoice = new Invoice(edtInvoiceNumber.getText().toString(),edtDate.getText().toString(), edtStudentID.getText().toString());
                    if (invoice != null) {
                        invoiceModify.addInvoice(invoice);
                    }
                    updateListInvoice();
                    setAdapter();
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateListInvoice(){
        invoiceList.clear();
        invoiceList.addAll(invoiceModify.getAllInvoice());
        if(invoiceAdapter!= null){
            invoiceAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Invoice invoiceItem = (Invoice) invoiceAdapter.getItem(info.position);
        final String id = invoiceItem.getInvoiceNumber();

        Toast.makeText(this, (lvHD.getItemAtPosition(info.position)).toString(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                int result = invoiceModify.deleteInvoice(id);
                if(result>0){
                    Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                    updateListInvoice();
                }else{
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_edit:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("Cập nhật Hóa đơn");
                dialog.setContentView(R.layout.dialog_add_invoice);
                final EditText edtInvoiceNumber, edtDate, edtStudentID;
                Button btnCancel, btnUpdate;

                edtInvoiceNumber=(EditText) dialog.findViewById(R.id.edtMaHoadon);
                edtDate=(EditText) dialog.findViewById(R.id.edtNgay);
                edtStudentID=(EditText) dialog.findViewById(R.id.edtMaSV);

                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate=(Button) dialog.findViewById(R.id.btnUpdate);

                Invoice invoice=invoiceModify.fetchInvoicetByID(id);
                edtInvoiceNumber.setText(invoice.getInvoiceNumber());
                edtDate.setText(invoice.getDate());
                edtStudentID.setText(invoice.getSudent_id());

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Invoice invoice = new Invoice(edtInvoiceNumber.getText().toString(), edtDate.getText().toString(), edtStudentID.getText().toString());
//                        subjectModify.updateSubject(subject);
//                        setAdapter();
//                        updateListSubject();

                        int result = invoiceModify.updateInvoice(invoice);
                        Toast.makeText(InvoiceList.this, "ID update: " + String.valueOf(id), Toast.LENGTH_SHORT).show();
                        if(result > 0){
//                            subjectModify.updateSubject(subject);
                            updateListInvoice();
                        }
                        else {
                            Toast.makeText(InvoiceList.this, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
