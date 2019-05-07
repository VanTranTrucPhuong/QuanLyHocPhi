package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.Dialog;
import android.content.Intent;
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

import com.example.vantrantrucphuong.quanlyhocphi.Adapter.StudentAdapter;
import com.example.vantrantrucphuong.quanlyhocphi.Database.StudentModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;
import com.example.vantrantrucphuong.quanlyhocphi.R;

public class StudentList extends AppCompatActivity {

    StudentModify studentModify;
    StudentAdapter adapter;
    ListView lvDS;
    public String masv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvDS=(ListView)findViewById(R.id.lvDS);
        studentModify=new StudentModify(this);
        display();
        registerForContextMenu(lvDS);
        setEvent();

    }

    public void display(){
        adapter=new StudentAdapter(this, studentModify.getStudentList(),true);
        lvDS.setAdapter(adapter);
    }
    private void setEvent() {
        lvDS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), InvoiceList.class);
                startActivity(intent);
            }
        });
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
            dialog.setTitle("Thêm mới sinh viên");
            dialog.setContentView(R.layout.dialog_add_student);
            final EditText edtName, edtID, edtPhone;
            Button btnCancel, btnInsert;

            edtName=(EditText) dialog.findViewById(R.id.edtName);
            edtID=(EditText) dialog.findViewById(R.id.edtID);
            edtPhone=(EditText) dialog.findViewById(R.id.edtPhone);

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
                    Student student=new Student(edtID.getText().toString(),edtName.getText().toString(), edtPhone.getText().toString());
                    Toast.makeText(StudentList.this, student.getStudent_id().toString(), Toast.LENGTH_SHORT).show();
                    studentModify.insert(student);
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

        Cursor cursor=(Cursor) lvDS.getItemAtPosition(info.position);
        final String id = cursor.getString(0);
        masv= id; //luu de load cai hoa don

        switch (item.getItemId()){
            case R.id.action_delete:
                studentModify.delete(id);
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                display();
                return true;
            case R.id.action_edit:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("Cập nhật sinh viên");
                dialog.setContentView(R.layout.dialog_add_student);
                final EditText edtName, edtID, edtPhone;
                Button btnCancel, btnUpdate;

                edtName=(EditText) dialog.findViewById(R.id.edtName);
                edtID=(EditText) dialog.findViewById(R.id.edtID);
                edtPhone=(EditText) dialog.findViewById(R.id.edtPhone);
                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate=(Button) dialog.findViewById(R.id.btnUpdate);

                Student student=studentModify.fetchStudentByID(id);
                edtID.setText(student.getStudent_id());
                edtPhone.setText(student.getPhoneNumber());
                edtName.setText(student.getName());

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Student student=new Student(edtID.getText().toString(), edtName.getText().toString(), edtPhone.getText().toString());
                        studentModify.update(student);
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