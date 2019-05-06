package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;


/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */
public class StudentModify {
    private DBHelper dbHelper;

    public StudentModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    //PT Them
    public void insert(Student student){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_ID,student.getStudent_id());
        values.put(DBHelper.KEY_NAME, student.getName());
        values.put(DBHelper.KEY_PHONE, student.getPhoneNumber());

        db.insert(DBHelper.TABLE_NAME,null,values);
        db.close();
    }

    //PT Sua
    public void update(Student student){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_ID,student.getStudent_id());
        values.put(DBHelper.KEY_NAME, student.getName());
        values.put(DBHelper.KEY_PHONE, student.getPhoneNumber());

        db.update(DBHelper.TABLE_NAME,values, DBHelper.KEY_ID+ "=?",new String[]{student.getStudent_id()});
        db.close();

    }

    //PT xoa
    public void delete(String student_id){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME,DBHelper.KEY_ID+"=?",new String[]{student_id});
        db.close();
    }


    //lay tat ca du lieu trong bang
    public Cursor getStudentList(){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME,new String[]{DBHelper.KEY_ID,DBHelper.KEY_NAME,DBHelper.KEY_PHONE},null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Lay 1 du lieu
    public Student fetchStudentByID(String student_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME,new String[]{DBHelper.KEY_ID,DBHelper.KEY_NAME,DBHelper.KEY_PHONE},DBHelper.KEY_ID+"=?",new String[]{String.valueOf(student_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }

}
