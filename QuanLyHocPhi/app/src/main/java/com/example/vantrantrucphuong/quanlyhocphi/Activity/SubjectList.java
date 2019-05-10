package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vantrantrucphuong.quanlyhocphi.Adapter.SubjectAdapter;
import com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper;
import com.example.vantrantrucphuong.quanlyhocphi.Database.SubjectModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.R.id.lvDM;

public class SubjectList extends AppCompatActivity {

    SubjectModify subjectModify;
    private ListView lvSubject;
    private DBHelper dbHelper;
    private SubjectAdapter customAdapter;
    private List<Subject> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvSubject = (ListView) findViewById(lvDM);

        subjectModify=new SubjectModify(this);
        subjectList = subjectModify.getAllSubject();
        setAdapter();
        registerForContextMenu(lvSubject);
    }


    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new SubjectAdapter(this, R.layout.item_list_subject, subjectList);
            lvSubject.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvSubject.setSelection(customAdapter.getCount()-1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {
            final Dialog dialog=new Dialog(this);
            dialog.setTitle("Thêm mới môn học");
            dialog.setContentView(R.layout.dialog_add_subject);
            final EditText edtNameSub, edtIDSub, edtCreditNumber;
            Button btnCancel, btnInsert;

            edtNameSub=(EditText) dialog.findViewById(R.id.edtNameSub);
            edtIDSub=(EditText) dialog.findViewById(R.id.edtIDSub);
            edtCreditNumber=(EditText) dialog.findViewById(R.id.edtCreditNumber);

            btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
            btnInsert=(Button) dialog.findViewById(R.id.btnUpdate);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Subject subject = new Subject(edtIDSub.getText().toString(),edtNameSub.getText().toString(), edtCreditNumber.getText().toString());
                    boolean flag = true;
                    for(int i = 0; i < subjectList.size(); i++){
                        if(subject.getSubject_id().equals(subjectList.get(i).getSubject_id())){
                            flag = false;
                        }
                    }
                    if (flag == false) {
                        Toast.makeText(SubjectList.this, "Mã môn học này đã tồn tại !!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(subject != null){
                            subjectModify.addSubject(subject);
                        }
                        updateListSubject();
                        setAdapter();
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateListSubject(){
        subjectList.clear();
        subjectList.addAll(subjectModify.getAllSubject());
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }

    //ContextMenu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Subject subjectItem = (Subject) customAdapter.getItem(info.position);
        final String id = subjectItem.getSubject_id();

        Toast.makeText(this, (lvSubject.getItemAtPosition(info.position)).toString(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(SubjectList.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int result = subjectModify.deleteSubject(id);
                        if(result>0){
                            Toast.makeText(getApplicationContext(),"Xóa thành công", Toast.LENGTH_SHORT).show();
                            updateListSubject();
                        }else{
                            Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();


                return true;
            case R.id.action_edit:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("Cập nhật môn học");
                dialog.setContentView(R.layout.dialog_add_subject);
                final EditText edtNameSub, edtIDSub, edtCreditNumber;
                Button btnCancel, btnUpdate;

                edtNameSub=(EditText) dialog.findViewById(R.id.edtNameSub);
                edtIDSub=(EditText) dialog.findViewById(R.id.edtIDSub);
                edtCreditNumber=(EditText) dialog.findViewById(R.id.edtCreditNumber);
                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate=(Button) dialog.findViewById(R.id.btnUpdate);

                Subject subject=subjectModify.fetchSubjectByID(id);
                edtIDSub.setText(subject.getSubject_id());
                edtCreditNumber.setText(subject.getCreditNumber());
                edtNameSub.setText(subject.getSubjectName());

                edtIDSub.setEnabled(false);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Subject subject = new Subject( edtIDSub.getText().toString(), edtNameSub.getText().toString(), edtCreditNumber.getText().toString());
//                        subjectModify.updateSubject(subject);
//                        setAdapter();
//                        updateListSubject();

                        int result = subjectModify.updateSubject(subject);
                        Toast.makeText(SubjectList.this, "ID update: " + String.valueOf(id), Toast.LENGTH_SHORT).show();
                        if(result > 0){
//                            subjectModify.updateSubject(subject);
                            updateListSubject();
                        }
                        else {
                            Toast.makeText(SubjectList.this, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
