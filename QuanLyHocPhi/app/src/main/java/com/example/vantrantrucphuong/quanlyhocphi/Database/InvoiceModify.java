package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.vantrantrucphuong.quanlyhocphi.Activity.InvoiceList;
import com.example.vantrantrucphuong.quanlyhocphi.Activity.StudentList;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class InvoiceModify {
    public static DBHelper dbHelper;
    StudentList studentList = new StudentList();
    public String masv = studentList.masv;

    public InvoiceModify(Context context) {
        dbHelper= new DBHelper(context);
    }
    //lay tat ca du lieu trong bang
    public ArrayList<Invoice> getAllInvoice() {
        ArrayList<Invoice> data = new ArrayList<>();
        String selectQuery = "SELECT * FROM invoice" ;
        SQLiteDatabase db =  dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                data.add( new Invoice(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        db.close();
        return data;
    }

    public Cursor getInvoiceList(){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME_INVOICE,new String[]{DBHelper.KEY_ID_INVOICE,DBHelper.KEY_DATE,DBHelper.KEY_ID},null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public static void insert(Invoice invoice){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_ID_INVOICE,invoice.getInvoiceNumber());
        values.put(DBHelper.KEY_DATE, invoice.getDate());
        values.put(DBHelper.KEY_ID, invoice.getSudent_id());
        db.insert(DBHelper.TABLE_NAME_INVOICE,null,values);
        db.close();
    }

    public Invoice fetchInvoiceByID(String invoice_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME_INVOICE,new String[]{DBHelper.KEY_ID_INVOICE,DBHelper.KEY_DATE,DBHelper.KEY_ID},DBHelper.KEY_ID_INVOICE+"=?",new String[]{String.valueOf(invoice_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Invoice(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }

//    insert invoice vao database




}
