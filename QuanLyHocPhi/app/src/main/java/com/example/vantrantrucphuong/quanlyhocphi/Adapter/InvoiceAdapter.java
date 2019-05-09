package com.example.vantrantrucphuong.quanlyhocphi.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.List;

public class InvoiceAdapter extends ArrayAdapter<Invoice> {

    private Context context;
    private int resoure;
    private List<Invoice> listInvoice;

    public InvoiceAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Invoice> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resoure=resource;
        this.listInvoice=objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_invoice,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView)convertView.findViewById(R.id.txtInvoiceNumber);
            viewHolder.tvDate = (TextView)convertView.findViewById(R.id.txtDate);
            viewHolder.tvStudent = (TextView)convertView.findViewById(R.id.txtStudentID);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Invoice invoice = listInvoice.get(position);
        viewHolder.tvId.setText(String.valueOf(invoice.getInvoice_id()));
        viewHolder.tvDate.setText(invoice.getInvoice_date());
        viewHolder.tvStudent.setText(invoice.getInvoice_student());

        return convertView;
    }

    public class ViewHolder{

        private TextView tvId;
        private TextView tvDate;
        private TextView tvStudent;
    }
}
