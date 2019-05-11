package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Infor;

import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.TABLE_NAME_INFOR;

/**
 * Created by Van Tran Truc Phuong on 5/11/2019.
 */

public class InforModify {
    private final String TAG = "DBManager";
    private DBHelper dbHelper;

    public InforModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void addDetail(Infor detail) {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put(DBHelper.KEY_SUB_ORDER, Student.getId());
        values.put(DBHelper.KEY_ID_INVOICE, detail.getInvoice_id());
        values.put(DBHelper.KEY_ID_SUB, detail.getSub_id());
        values.put(DBHelper.KEY_COST, detail.getMoney());

        db.insert(TABLE_NAME_INFOR,null,values);
        db.close();
        Log.d(TAG, "addDetail Successfuly");
    }


}
