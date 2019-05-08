package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vantrantrucphuong.quanlyhocphi.Activity.InvoiceList;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;

import java.util.ArrayList;

public class InvoiceModify {
    public static DBHelper dbHelper;
    InvoiceList invoiceList = new InvoiceList();

    public InvoiceModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    //lay tat ca du lieu trong bang
    public ArrayList<Invoice> getAllInvoice() {
        ArrayList<Invoice> listInvoice = new ArrayList<>();
        String selectQuery = "SELECT * FROM invoice" ;
        SQLiteDatabase db =  dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Invoice invoice = new Invoice();
                invoice.setInvoiceNumber(cursor.getString(0));
                invoice.setDate(cursor.getString(2)+"");
                invoice.setSudent_id(cursor.getString(1));
                listInvoice.add(invoice);

            } while (cursor.moveToNext());
        }
        db.close();
        return listInvoice;
    }
    //add invoice
    public void addInvoice(Invoice invoice) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID_INVOICE, invoice.getInvoiceNumber());
        values.put(DBHelper.KEY_ID_STUDENT, invoice.getDate());
        values.put(DBHelper.KEY_DATE, invoice.getSudent_id());


        db.insert(DBHelper.TABLE_NAME_INVOICE, null, values);
        db.close();
        Log.d(DBHelper.TAG, "add Successfuly");
    }

//    update Invoice
    public int updateInvoice(Invoice invoice){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_ID_INVOICE, invoice.getInvoiceNumber());
        contentValues.put(DBHelper.KEY_ID_STUDENT, invoice.getSudent_id());
        contentValues.put(DBHelper.KEY_DATE, invoice.getDate() );
        return db.update(DBHelper.TABLE_NAME_INVOICE,contentValues,DBHelper.KEY_ID_INVOICE+"=?",new String[]{invoice.getInvoiceNumber()});
    }

//    Delete invoice
    public int deleteInvoice(String invoice_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(DBHelper.TABLE_NAME_INVOICE,DBHelper.KEY_ID_INVOICE+"=?",new String[] {invoice_id});
    }

    public Invoice fetchInvoicetByID(String invoice_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME_INVOICE,new String[]{DBHelper.KEY_ID_INVOICE,DBHelper.KEY_DATE,DBHelper.KEY_ID_STUDENT},DBHelper.KEY_ID_INVOICE+"=?",new String[]{String.valueOf(invoice_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Invoice(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }




}
