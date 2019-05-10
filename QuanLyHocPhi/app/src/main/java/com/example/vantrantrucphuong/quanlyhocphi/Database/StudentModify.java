package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;

import java.util.ArrayList;
import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.KEY_ID;
import static com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper.TABLE_NAME;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */
public class StudentModify{
    private final String TAG = "DBManager";
    private DBHelper dbHelper;

    public StudentModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put(DBHelper.KEY_SUB_ORDER, Student.getId());
        values.put(DBHelper.KEY_ID, student.getStudent_id());
        values.put(DBHelper.KEY_NAME, student.getName());
        values.put(DBHelper.KEY_PHONE, student.getPhoneNumber());

        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d(TAG, "addStudent Successfuly");
    }

    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();;
                student.setStudent_id(cursor.getString(0));
                student.setName(cursor.getString(1)+"");
                student.setPhoneNumber(cursor.getString(2));
                listStudent.add(student);

            } while (cursor.moveToNext());
        }
        db.close();
        return listStudent;
    }

    public int updateStudent(Student student){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_ID,student.getStudent_id());
        contentValues.put(DBHelper.KEY_NAME,student.getName());
        contentValues.put(DBHelper.KEY_PHONE,student.getPhoneNumber());

        return db.update(TABLE_NAME,contentValues,KEY_ID+"=?",new String[]{String.valueOf(student.getStudent_id())});
    }

    public int deleteStudent(String student_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, KEY_ID+"=?",new String[] {student_id});
    }


    //Lay 1 du lieu
    public Student fetchStudentByID(String student_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(TABLE_NAME,new String[]{KEY_ID,DBHelper.KEY_NAME,DBHelper.KEY_PHONE}, KEY_ID+"=?",new String[]{student_id},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }
}
