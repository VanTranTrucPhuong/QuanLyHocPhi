package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
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
import com.example.vantrantrucphuong.quanlyhocphi.Database.SubjectModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Subject;
import com.example.vantrantrucphuong.quanlyhocphi.R;

public class SubjectList extends AppCompatActivity {

    SubjectModify subjectModify;
    SubjectAdapter adapter;
    ListView lvDM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvDM=(ListView)findViewById(R.id.lvDM);
        subjectModify=new SubjectModify(this);
        display();

        registerForContextMenu(lvDM);

    }

    public void display(){
        adapter=new SubjectAdapter(this, subjectModify.getSubjectList(),true);
        lvDM.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
                    Toast.makeText(SubjectList.this, subject.getSubject_id().toString(), Toast.LENGTH_SHORT).show();
                    subjectModify.insert(subject);
                    display();
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        Cursor cursor=(Cursor) lvDM.getItemAtPosition(info.position);
        final String id = cursor.getString(0);

        switch (item.getItemId()){
            case R.id.action_delete:
                subjectModify.delete(id);
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                display();
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

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Subject subject = new Subject(edtIDSub.getText().toString(), edtNameSub.getText().toString(), edtCreditNumber.getText().toString());
                        subjectModify.update(subject);
                        display();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}