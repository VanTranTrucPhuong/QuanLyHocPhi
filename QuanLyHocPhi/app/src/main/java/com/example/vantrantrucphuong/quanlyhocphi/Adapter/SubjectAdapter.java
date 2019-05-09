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

import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.List;

public class SubjectAdapter extends ArrayAdapter<Subject> {

    private Context context;
    private int resoure;
    private List<Subject> listSubject;

    public SubjectAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Subject> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resoure=resource;
        this.listSubject=objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_subject,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView)convertView.findViewById(R.id.txtSubID);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.txtSubName);
            viewHolder.tvCreditNumber = (TextView)convertView.findViewById(R.id.txtSubCreditNumber);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Subject subject = listSubject.get(position);
        viewHolder.tvId.setText(String.valueOf(subject.getSubject_id()));
        viewHolder.tvName.setText(subject.getSubjectName());
        viewHolder.tvCreditNumber.setText(subject.getCreditNumber());

        return convertView;
    }

    public class ViewHolder{

        private TextView tvId;
        private TextView tvName;
        private TextView tvCreditNumber;
    }
}
