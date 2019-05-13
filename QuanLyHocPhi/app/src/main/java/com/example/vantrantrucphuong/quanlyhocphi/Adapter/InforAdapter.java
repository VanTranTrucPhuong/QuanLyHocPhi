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

import com.example.vantrantrucphuong.quanlyhocphi.Model.Infor;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Invoice;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.List;

public class InforAdapter extends ArrayAdapter<Infor> {

    private Context context;
    private int resoure;
    private List<Infor> listInfor;

    public InforAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Infor> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resoure=resource;
        this.listInfor = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_infor,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView)convertView.findViewById(R.id.txtInfor);
            viewHolder.tvSub = (TextView)convertView.findViewById(R.id.txtInforSb);
            viewHolder.tvMoney = (TextView)convertView.findViewById(R.id.txtMoney);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Infor infor = listInfor.get(position);
        viewHolder.tvId.setText(String.valueOf(infor.getInvoice_id()));
        viewHolder.tvSub.setText(infor.getSub_id());
        viewHolder.tvMoney.setText(infor.getMoney());

        return convertView;
    }

    public class ViewHolder{

        private TextView tvId;
        private TextView tvSub;
        private TextView tvMoney;
    }
}
