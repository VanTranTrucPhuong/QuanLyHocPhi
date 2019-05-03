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

public class SubjectAdapter extends CursorAdapter{
    public SubjectAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list_subject,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtName, txtID, txtCreditNumber;
        txtName=(TextView)view.findViewById(R.id.txtSubName);
        txtID=(TextView)view.findViewById(R.id.txtSubID);
        txtCreditNumber=(TextView)view.findViewById(R.id.txtSubCreditNumber);

        txtID.setText(cursor.getString(0));
        txtName.setText(cursor.getString(1));
        txtCreditNumber.setText(cursor.getString(2));
    }
}
