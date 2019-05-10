package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.KEY_ID_SUB;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.KEY_CREDITNUMBER;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.TABLE_NAME_SUB;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */
public class SubjectModify {
    private final String TAG = "DBManager";
    private DBHelper dbHelper;

    public SubjectModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void addSubject(Subject subject) {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put(DBHelper.KEY_SUB_ORDER, subject.getId());
        values.put(DBHelper.KEY_ID_SUB, subject.getSubject_id());
        values.put(DBHelper.KEY_NAME_SUB, subject.getSubjectName());
        values.put(DBHelper.KEY_CREDITNUMBER, subject.getCreditNumber());

        db.insert(TABLE_NAME_SUB,null,values);
        db.close();
        Log.d(TAG, "addStudent Successfuly");
    }

    public List<Subject> getAllSubject() {
        List<Subject> listSubject = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME_SUB;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();
//                subject.setId(cursor.getInt(0));
//                subject.setSubject_id(cursor.getString(1));
//                subject.setSubjectName(cursor.getString(2)+"");
//                subject.setCreditNumber(cursor.getString(3));
                subject.setSubject_id(cursor.getString(0));
                subject.setSubjectName(cursor.getString(1)+"");
                subject.setCreditNumber(cursor.getString(2));
                listSubject.add(subject);

            } while (cursor.moveToNext());
        }
        db.close();
        return listSubject;
    }

    public int updateSubject(Subject subject){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBHelper.KEY_SUB_ORDER,subject.getId());
        contentValues.put(DBHelper.KEY_ID_SUB,subject.getSubject_id());
        contentValues.put(DBHelper.KEY_NAME_SUB,subject.getSubjectName());
        contentValues.put(DBHelper.KEY_CREDITNUMBER,subject.getCreditNumber());

        return db.update(TABLE_NAME_SUB,contentValues,KEY_ID_SUB+"=?",new String[]{String.valueOf(subject.getSubject_id())}); //thay dooi
    }

    public int deleteSubject(String subject_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_NAME_SUB,KEY_ID_SUB+"=?",new String[] {String.valueOf(subject_id)});
    }


    //Lay 1 du lieu
    public Subject fetchSubjectByID(String subject_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(TABLE_NAME_SUB,new String[]{DBHelper.KEY_ID_SUB,DBHelper.KEY_NAME_SUB,DBHelper.KEY_CREDITNUMBER},DBHelper.KEY_ID_SUB+"=?",new String[]{String.valueOf(subject_id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
//        return new Subject(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        return new Subject(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }




}
