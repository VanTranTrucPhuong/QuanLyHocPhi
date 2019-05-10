package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.KEY_INVOICE_ORDER;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.TABLE_NAME_INVOICE;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */
public class InvoiceModify {
    private final String TAG = "DBManager";
    private DBHelper dbHelper;

    public InvoiceModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void add(Invoice invoice) {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put(DBHelper.KEY_SUB_ORDER, subject.getId());
        values.put(DBHelper.KEY_ID_INVOICE, invoice.getInvoice_id());
        values.put(DBHelper.KEY_DATE_INVOICE, invoice.getInvoice_date());
        values.put(DBHelper.KEY_STUDENT_INVOICE, invoice.getInvoice_student());

        db.insert(TABLE_NAME_INVOICE,null,values);
        db.close();
        Log.d(TAG, " Successfuly");
    }

    public List<Invoice> getAll(String masv) {
        List<Invoice> listInvoice = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_INVOICE +" WHERE  "+DBHelper.KEY_STUDENT_INVOICE +" = '"+ masv +"' "  ;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Invoice invoice = new Invoice();
                invoice.setId(cursor.getInt(0));
                invoice.setInvoice_id(cursor.getString(1));
                invoice.setInvoice_date(cursor.getString(2)+"");
                invoice.setInvoice_student(cursor.getString(3));
                listInvoice.add(invoice);

            } while (cursor.moveToNext());
        }
        db.close();
        return listInvoice;
    }

    public int update(Invoice invoice){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_INVOICE_ORDER,invoice.getId());
        contentValues.put(DBHelper.KEY_ID_INVOICE,invoice.getInvoice_id());
        contentValues.put(DBHelper.KEY_DATE_INVOICE,invoice.getInvoice_date());
        contentValues.put(DBHelper.KEY_STUDENT_INVOICE,invoice.getInvoice_student());

        return db.update(TABLE_NAME_INVOICE,contentValues,KEY_INVOICE_ORDER+"=?",new String[]{String.valueOf(invoice.getId())});
    }

    public int delete(int invoice_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_NAME_INVOICE,KEY_INVOICE_ORDER+"=?",new String[] {String.valueOf(invoice_id)});
    }


    //Lay 1 du lieu
    public Invoice fetchByID(int invoice_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(TABLE_NAME_INVOICE,new String[]{DBHelper.KEY_INVOICE_ORDER,DBHelper.KEY_ID_INVOICE,DBHelper.KEY_DATE_INVOICE,DBHelper.KEY_STUDENT_INVOICE},DBHelper.KEY_INVOICE_ORDER+"=?",new String[]{String.valueOf(invoice_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Invoice(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
    }





}
