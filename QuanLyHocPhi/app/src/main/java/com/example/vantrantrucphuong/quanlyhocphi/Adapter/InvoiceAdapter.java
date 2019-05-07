package com.example.vantrantrucphuong.quanlyhocphi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.R;

public class InvoiceAdapter extends ArrayAdapter<Invoice>{
    Context context;
    int layoutResourceId;
    ArrayList<Invoice> data = null;

    public InvoiceAdapter(Context context, int layoutResourceId, ArrayList<Invoice>data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    static class ViewHolder {
        TextView txtNumber;
        TextView txtDate;
        TextView txtStudent;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder viewHolder = null;
        if(row == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtNumber = (TextView) row.findViewById(R.id.bill_number);
            viewHolder.txtDate = (TextView) row.findViewById(R.id.bill_date);
            viewHolder.txtStudent = (TextView) row.findViewById(R.id.bill_student);

        }
        else {
            viewHolder = (ViewHolder) row.getTag();
        }
        Invoice customListView = data.get(position);
        viewHolder.txtNumber.setText(customListView.getInvoiceNumber());
        viewHolder.txtDate.setText(customListView.getDate());
        viewHolder.txtStudent.setText(customListView.getSudent_id());
        return row;
    }


}



