package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;



/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */
public class SubjectModify {
    private DBHelper dbHelper;

    public SubjectModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    //PT Them
    public void insert(Subject subject){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_ID_SUB, subject.getSubject_id());
        values.put(DBHelper.KEY_NAME_SUB, subject.getSubjectName());
        values.put(DBHelper.KEY_CREDITNUMBER, subject.getCreditNumber());

        db.insert(DBHelper.TABLE_NAME_SUB,null,values);
        db.close();
    }

    //PT Sua
    public void update(Subject subject){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_ID_SUB,subject.getSubject_id());
        values.put(DBHelper.KEY_NAME_SUB, subject.getSubjectName());
        values.put(DBHelper.KEY_CREDITNUMBER, subject.getCreditNumber());

        db.update(DBHelper.TABLE_NAME_SUB,values, DBHelper.KEY_ID_SUB+ "=?",new String[]{String.valueOf(subject.getSubject_id())});
        db.close();

    }

    //PT xoa
    public void delete(String subject_id){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME_SUB,DBHelper.KEY_ID_SUB+"=?",new String[]{subject_id});
        db.close();
    }


    //lay tat ca du lieu trong bang
    public Cursor getSubjectList(){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME_SUB,new String[]{DBHelper.KEY_ID_SUB,DBHelper.KEY_NAME_SUB,DBHelper.KEY_CREDITNUMBER},null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Lay 1 du lieu
    public Subject fetchSubjectByID(String subject_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME_SUB,new String[]{DBHelper.KEY_ID_SUB,DBHelper.KEY_NAME_SUB,DBHelper.KEY_CREDITNUMBER},DBHelper.KEY_ID_SUB+"=?",new String[]{String.valueOf(subject_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Subject(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }

}
