package com.example.vantrantrucphuong.quanlyhocphi.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.vantrantrucphuong.quanlyhocphi.R;

/**
 * Created by Van Tran Truc Phuong on 5/1/2019.
 */


public class StudentAdapter extends CursorAdapter {
    public StudentAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list_student,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtName, txtID, txtPhoneNumber;
        txtName=(TextView)view.findViewById(R.id.txtStName);
        txtID=(TextView)view.findViewById(R.id.txtStID);
        txtPhoneNumber=(TextView)view.findViewById(R.id.txtStPhoneNumber);

        txtID.setText(cursor.getString(0));
        txtName.setText(cursor.getString(1));
        txtPhoneNumber.setText(cursor.getString(2));
    }
}
