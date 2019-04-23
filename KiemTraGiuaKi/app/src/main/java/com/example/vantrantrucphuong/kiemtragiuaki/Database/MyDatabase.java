package com.example.vantrantrucphuong.kiemtragiuaki.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Van Tran Truc Phuong on 4/23/2019.
 */

public class MyDatabase extends SQLiteOpenHelper {
    private static String DB_NAME = "dbQLHP.db";
    private static int DB_VERSION = 1;

//    Define table SINHVIEN
    private static final String TB_SINHVIENS = "tbSinhVien";
    private static final String COL_SINHVIEN_MASV = "sinhvien_id";
    private static final String COL_SINHVIEN_HOTEN = "sinhvien_hoten";
    private static final String COL_SINHVIEN_SODT = "sinhvien_sodt";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        Script to create table
        String scriptTBSinhViens = "CREATE TABLE " + TB_SINHVIENS + "(" +
                COL_SINHVIEN_MASV + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_SINHVIEN_HOTEN + " TEXT, " +
                COL_SINHVIEN_SODT + " TEXT)";

//        EXCECUTE SCRIPT
        sqLiteDatabase.execSQL(scriptTBSinhViens);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_SINHVIENS);
        onCreate(sqLiteDatabase);
    }
}
