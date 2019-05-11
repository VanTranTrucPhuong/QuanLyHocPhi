package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice_Info;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.KEY_ID_INVOICE;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.KEY_ID_SUB;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.TABLE_NAME_INFOR;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.TABLE_NAME_INVOICE;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */
public class InforModify {
    private final String TAG = "DBManager";
    private DBHelper dbHelper;

    public InforModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void add(Invoice_Info info) {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put(DBHelper.KEY_SUB_ORDER, subject.getId());
        values.put(DBHelper.KEY_ID_INVOICE, info.getInvoice_id());
        values.put(KEY_ID_SUB, info.getSubject_id());
        values.put(DBHelper.KEY_COST, info.getCost());

        db.insert(TABLE_NAME_INFOR,null,values);
        db.close();
        Log.d(TAG, " Successfuly");
    }

    public List<Invoice_Info> getAll() {
        List<Invoice_Info> listInvoice = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_INFOR +" "  ;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Invoice_Info infor = new Invoice_Info();
                infor.setInvoice_id(cursor.getString(0));
                infor.setSubject_id(cursor.getString(1)+"");
                infor.setCost(cursor.getString(2));
                listInvoice.add(infor);

            } while (cursor.moveToNext());
        }
        db.close();
        return listInvoice;
    }

    public int update(Invoice_Info infor){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_ID_INVOICE,infor.getInvoice_id());
        contentValues.put(KEY_ID_SUB,infor.getSubject_id());
        contentValues.put(DBHelper.KEY_COST,infor.getCost());

        return db.update(TABLE_NAME_INFOR,contentValues,KEY_ID_SUB+"=?",new String[]{String.valueOf(infor.getInvoice_id())});
    }

    public int delete(String invoice_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_NAME_INFOR,KEY_ID_SUB+"=?",new String[] {String.valueOf(invoice_id)});
    }


    //Lay 1 du lieu
    public Invoice fetchByID(String invoice_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(TABLE_NAME_INVOICE,new String[]{DBHelper.KEY_ID_INVOICE,DBHelper.KEY_DATE_INVOICE,DBHelper.KEY_STUDENT_INVOICE},DBHelper.KEY_ID_INVOICE+"=?",new String[]{String.valueOf(invoice_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Invoice(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }





}
