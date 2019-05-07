package com.example.vantrantrucphuong.quanlyhocphi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.R;

public class InvoiceAdapter extends ArrayAdapter<Invoice>{
    private Context context;
    private int layoutResourceId;
    private ArrayList<Invoice> listInvoice = null;

    public InvoiceAdapter(@NonNull Context context, @LayoutRes int layoutResourceId,@NonNull ArrayList<Invoice>objects){
        super(context, layoutResourceId, objects);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listInvoice = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull  ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_invoice,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.txtInvoiceNumber);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.txtDate);
            viewHolder.tvStudentID = (TextView) convertView.findViewById(R.id.txtStudentID);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Invoice invoice = listInvoice.get(position);
        viewHolder.tvNumber.setText(invoice.getInvoiceNumber());
        viewHolder.tvDate.setText(invoice.getDate());
        viewHolder.tvStudentID.setText(invoice.getSudent_id());

        return convertView;
    }
    public class ViewHolder {
        private  TextView tvNumber;
        private TextView tvDate;
        private TextView tvStudentID;
    }


}



