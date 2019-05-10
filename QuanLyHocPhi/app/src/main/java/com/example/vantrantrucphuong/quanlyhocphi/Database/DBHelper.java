package com.example.vantrantrucphuong.quanlyhocphi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //    DATABASE_NAME
    private static final String DATABASE_NAME = "db_student";
    public static String TAG = "DBManager";

    //    TABLE_STUDENT
    public static final String TABLE_NAME = "student";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";


    //    TABLE BIENLAIHOCPHI
    public static final String TABLE_NAME_INVOICE = "invoice";
    public static final String KEY_INVOICE_ORDER = "_id";
    public static final String KEY_ID_INVOICE = "invoice_id";
    public static final String KEY_STUDENT_INVOICE = "invoice_student";
    public static final String KEY_DATE_INVOICE = "invoice_date";

    //    TABLE THONGTINBIENLAI
    public static final String TABLE_NAME_INFOR = "information";
    //    public static final String KEY_ID_INVOICE = "invoice_id";
    //    public static final String KEY_ID_STUDENT = "student_id";
    public static final String KEY_COST = "cost";

    //    TABLE MONHOC
    public static final String TABLE_NAME_SUB = "subject";
    public static final String KEY_ID_SUB = "sub_id";
    public static final String KEY_NAME_SUB = "sub_name";
    public static final String KEY_CREDITNUMBER = "sub_creditnumber";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //    Table Create Statements
//    Student table create statement
    public static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_NAME + " ("
            + KEY_ID + " TEXT PRIMARY KEY NOT NULL,"
            + KEY_NAME + " TEXT,"
            + KEY_PHONE + " TEXT)";

    // Subject table create statement
    public static final String CREATE_TABLE_SUBJECT = "CREATE TABLE " + TABLE_NAME_SUB + " ("
            + KEY_ID_SUB + " VARCHAR(10) PRIMARY KEY NOT NULL,"
            + KEY_NAME_SUB + " TEXT,"
            + KEY_CREDITNUMBER + " TEXT)";

    //Invoice table create statement
    private static final String CREATE_TABLE_INVOICE= "CREATE TABLE " + TABLE_NAME_INVOICE + " ("
            + KEY_INVOICE_ORDER + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + KEY_ID_INVOICE + " TEXT ,"
            + KEY_DATE_INVOICE + " TEXT,"
            + KEY_STUDENT_INVOICE + " TEXT)";

    //    Information of invoice
    private static final String CREATE_TABLE_INFOR= "CREATE TABLE " + TABLE_NAME_INFOR + " ("
            + KEY_ID_INVOICE + " TEXT PRIMARY KEY NOT NULL,"
            + KEY_ID_SUB + " TEXT,"
            + KEY_COST + " TEXT)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_SUBJECT);
        db.execSQL(CREATE_TABLE_INVOICE);
        db.execSQL(CREATE_TABLE_INFOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_SUB);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_INVOICE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_INFOR);


        // create new tables
        onCreate(db);
    }
}
