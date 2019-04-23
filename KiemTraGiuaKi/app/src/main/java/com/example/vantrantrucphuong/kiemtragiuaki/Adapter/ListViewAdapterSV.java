package com.example.vantrantrucphuong.kiemtragiuaki.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vantrantrucphuong.kiemtragiuaki.Model.CustomListViewSV;
import com.example.vantrantrucphuong.kiemtragiuaki.R;

import java.util.ArrayList;

/**
 * Created by Van Tran Truc Phuong on 4/20/2019.
 */

public class ListViewAdapterSV extends ArrayAdapter<CustomListViewSV> {
    Context context;
    int layoutResourceId;
    ArrayList<CustomListViewSV> data = null;

    public ListViewAdapterSV(Context context, int layoutResourceId, ArrayList<CustomListViewSV>data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtText;
        TextView txtSdt;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder viewHolder = null;
        if(row == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(layoutResourceId, parent, false);
//            setControl()
            viewHolder = new ViewHolder();
            viewHolder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            viewHolder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            viewHolder.txtText = (TextView) row.findViewById(R.id.txtText);
            viewHolder.txtSdt = (TextView) row.findViewById(R.id.txtSdt);
        }
        else {
            viewHolder = (ViewHolder) row.getTag();
        }

        CustomListViewSV customListView = data.get(position);
        viewHolder.imgIcon.setImageResource(customListView.getImageId());
        viewHolder.txtTitle.setText(customListView.getTitle());
        viewHolder.txtText.setText(customListView.getText());
        viewHolder.txtSdt.setText(customListView.getSdt());
        return row;
    }
}

