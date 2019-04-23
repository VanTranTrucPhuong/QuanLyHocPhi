package com.example.vantrantrucphuong.kiemtragiuaki.Adapter;

/**
 * Created by Van Tran Truc Phuong on 4/20/2019.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vantrantrucphuong.kiemtragiuaki.Model.CustomListViewBL;
import com.example.vantrantrucphuong.kiemtragiuaki.R;

import java.util.ArrayList;

public class ListViewAdapterBL extends ArrayAdapter<CustomListViewBL> {
    Context context;
    int layoutResourceId;
    ArrayList<CustomListViewBL> data = null;

    public ListViewAdapterBL(Context context, int layoutResourceId, ArrayList<CustomListViewBL>data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    static class ViewHolder {
        TextView txtNumber;
        TextView txtDate;
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
        }
        else {
            viewHolder = (ViewHolder) row.getTag();
        }

        CustomListViewBL customListView = data.get(position);

        viewHolder.txtNumber.setText(customListView.getBill_number());
        viewHolder.txtDate.setText(customListView.getBill_date());
        return row;
    }
}
