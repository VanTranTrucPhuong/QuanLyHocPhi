package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vantrantrucphuong.quanlyhocphi.Adapter.InvoiceAdapter;
import com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper;
import com.example.vantrantrucphuong.quanlyhocphi.Database.InvoiceModify;
import com.example.vantrantrucphuong.quanlyhocphi.Database.SubjectModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.R.id.listViewInvoice;

public class InvoiceList extends AppCompatActivity {

    InvoiceModify invoiceModify;
    SubjectModify subjectModify;
    private ListView lvInvoice;
    private DBHelper dbHelper;
    private InvoiceAdapter customAdapter;
    private List<Invoice> invoiceList;
    private List<Subject> subjectList;
    public String masv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvInvoice = (ListView) findViewById(listViewInvoice);
        invoiceModify=new InvoiceModify(this);
        Intent intent = getIntent();
        masv= intent.getStringExtra(StudentList.masoSinhVien);
        invoiceList = invoiceModify.getAll(masv);

        //                Spinner MÔN HỌC
        subjectModify=new SubjectModify(this);
        subjectList = subjectModify.getAllSubject();
//                End Spinner MÔN HÓC
        setAdapter();
        registerForContextMenu(lvInvoice);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new InvoiceAdapter(this, R.layout.item_list_invoice, invoiceList);
            lvInvoice.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvInvoice.setSelection(customAdapter.getCount()-1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new) {
//            final Dialog dialog=new Dialog(this);
//            dialog.setTitle("Thêm mới");
//            dialog.setContentView(R.layout.dialog_add_invoice);
//            final EditText edtID, txtDate, edtStudent;
//            Button btnCancel, btnInsert;
//
//            edtID=(EditText) dialog.findViewById(R.id.edtInvoiceID);
//            txtDate=(TextView) dialog.findViewById(R.id.txtDate);
//            edtStudent=(EditText) dialog.findViewById(R.id.edtStudentID);
//
//            btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
//            btnInsert=(Button) dialog.findViewById(R.id.btnUpdate);
//
//            btnCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            btnInsert.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Invoice invoice = new Invoice(edtID.getText().toString(),txtDate.getText().toString(), edtStudent.getText().toString());
//                    if (invoice != null) {
//                        invoiceModify.add(invoice);
//                    }
//                    updateListInvoice();
//                    setAdapter();
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.show();
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateListInvoice(){
        invoiceList.clear();
        invoiceList.addAll(invoiceModify.getAll(masv));
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }

    //ContextMenu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_invoice,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Invoice invoiceItem = (Invoice) customAdapter.getItem(info.position);
        final String id = invoiceItem.getInvoice_id();

        Toast.makeText(this, (lvInvoice.getItemAtPosition(info.position)).toString(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){


            case R.id.action_delete:
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                int result = invoiceModify.delete(id);
                if(result>0){
                    Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                    updateListInvoice();
                }else{
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_edit:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("Cập nhật môn học");
                dialog.setContentView(R.layout.dialog_add_invoice);
                final EditText edtID,edtStudent;
                final TextView txtDate;
                Button btnCancel, btnUpdate;

                edtID=(EditText) dialog.findViewById(R.id.edtInvoiceID);
                txtDate=(TextView) dialog.findViewById(R.id.txtDate);
                edtStudent=(EditText) dialog.findViewById(R.id.edtStudentID);

                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate=(Button) dialog.findViewById(R.id.btnUpdate);

                Invoice invoice=invoiceModify.fetchInvoiceByID(id);
                edtID.setText(invoice.getInvoice_id());
                txtDate.setText(invoice.getInvoice_date());
                edtStudent.setText(invoice.getInvoice_student());

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Invoice invoice = new Invoice( edtID.getText().toString(), txtDate.getText().toString(), edtStudent.getText().toString());
                        int result = invoiceModify.update(invoice);
                        Toast.makeText(InvoiceList.this, "ID update: " + String.valueOf(id), Toast.LENGTH_SHORT).show();
                        if(result > 0){
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
            case R.id.action_ivoice_info:
                final Dialog dialogPlus=new Dialog(this);
                dialogPlus.setTitle("Chi Tiết Hóa Đơn");
                dialogPlus.setContentView(R.layout.dialog_add_infor);
                final EditText edtMaMH, edtMoney, edtInvoiceID;
                Button btnInsert, btnChooseSub;
                edtInvoiceID=(EditText) dialogPlus.findViewById(R.id.edtInvoiceID);

                edtMaMH =(EditText) dialogPlus.findViewById(R.id.edtMaMH);
                edtMoney =(EditText) dialogPlus.findViewById(R.id.edtMoney);

                invoice = invoiceModify.fetchInvoiceByID(id);
                edtInvoiceID.setText(invoice.getInvoice_id());
                edtInvoiceID.setEnabled(false);

                btnCancel=(Button) dialogPlus.findViewById(R.id.btnCancel);
                btnInsert=(Button) dialogPlus.findViewById(R.id.btnUpdate);
                btnChooseSub =(Button) dialogPlus.findViewById(R.id.btnChooseSub);

                btnChooseSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(InvoiceList.this);
                        builder.setTitle("Chọn mã môn học");                final List<String> list = new ArrayList<String>();
                        for(int i = 0; i < subjectList.size(); i++){
                            list.add(subjectList.get(i).getSubject_id());
                        }
                        final String[] types = list.toArray(new String[0]);
                        builder.setItems(types, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                String subjectID = types[which];
                                int money;
                                int creditNumber = 0;
                                Log.v("-----> " , "selected text = " + subjectID);
                                edtMaMH.setText(types[which]);
                                edtMaMH.setEnabled(false);
                                for(int i = 0; i < subjectList.size(); i++){
                                    if(subjectID.equals(subjectList.get(i).getSubject_id())){
                                        creditNumber = subjectList.get(i).getCreditNumber();
                                    }
                                }
                                money = 395000 * creditNumber;
                                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                edtMoney.setText(decimalFormat.format(money) + " VND");
                            }

                        });

                        builder.show();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();
                    }
                });


                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogPlus.dismiss();
                    }
                });

                dialogPlus.show();
                return true;
        }

    return super.onContextItemSelected(item);
    }



}
