package com.example.vantrantrucphuong.quanlyhocphi.Activity;

import android.app.Dialog;
import android.content.Intent;
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
import com.example.vantrantrucphuong.quanlyhocphi.Database.DBHelper;
import com.example.vantrantrucphuong.quanlyhocphi.Database.StudentModify;
import com.example.vantrantrucphuong.quanlyhocphi.Model.Student;
import com.example.vantrantrucphuong.quanlyhocphi.R;

import java.util.List;

import static com.example.vantrantrucphuong.quanlyhocphi.R.id.edtID;
import static com.example.vantrantrucphuong.quanlyhocphi.R.id.edtName;
import static com.example.vantrantrucphuong.quanlyhocphi.R.id.edtPhone;
import static com.example.vantrantrucphuong.quanlyhocphi.R.id.lvDS;

public class StudentList extends AppCompatActivity {

    StudentModify studentModify;
    private ListView lvStudent;
    private DBHelper dbHelper;
    private StudentAdapter customAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvStudent = (ListView) findViewById(lvDS);

        studentModify = new StudentModify(this);
        studentList = studentModify.getAllStudent();
        setAdapter();
        setEventClickItem();
        registerForContextMenu(lvStudent);
    }

    private void setEventClickItem() {
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Student studentItem = (Student) customAdapter.getItem(i);
                final String id = studentItem.getStudent_id();
                
                Toast.makeText(StudentList.this, "ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new StudentAdapter(this, R.layout.item_list_student, studentList);
            lvStudent.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {
            final Dialog dialog=new Dialog(this);
            dialog.setTitle("Thêm mới môn học");
            dialog.setContentView(R.layout.dialog_add_student);
            final EditText edtNameSt, edtIDSt, edtPhoneNumber;
            Button btnCancel, btnInsert;

            edtNameSt=(EditText) dialog.findViewById(edtName);
            edtIDSt=(EditText) dialog.findViewById(edtID);
            edtPhoneNumber=(EditText) dialog.findViewById(edtPhone);

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
                    Student Student = new Student(edtIDSt.getText().toString(),edtNameSt.getText().toString(), edtPhoneNumber.getText().toString());
                    if (Student != null) {
                        studentModify.addStudent(Student);
                    }
                    updateListStudent();
                    setAdapter();
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateListStudent(){
        studentList.clear();
        studentList.addAll(studentModify.getAllStudent());
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
        Student studentItem = (Student) customAdapter.getItem(info.position);
//        Cursor cursor=(Cursor) lvStudent.getItemAtPosition(info.position);
        final String id = studentItem.getStudent_id();

        Toast.makeText(this, (lvStudent.getItemAtPosition(info.position)).toString(), Toast.LENGTH_SHORT).show();
//        final int id = 2;

        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                int result = studentModify.deleteStudent(id);
                if(result>0){
                    Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                    updateListStudent();
                }else{
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_edit:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("Cập nhật môn học");
                dialog.setContentView(R.layout.dialog_add_student);
                final EditText edtNameSt, edtIDSt, edtPhoneNumber;
                Button btnCancel, btnUpdate;

                edtNameSt = (EditText) dialog.findViewById(edtName);
                edtIDSt =(EditText) dialog.findViewById(edtID);
                edtPhoneNumber=(EditText) dialog.findViewById(edtPhone);
                btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate=(Button) dialog.findViewById(R.id.btnUpdate);

                Student student = studentModify.fetchStudentByID(id);
                edtIDSt.setText(student.getStudent_id());
                edtPhoneNumber.setText(student.getPhoneNumber());
                edtNameSt.setText(student.getName());

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Student Student = new Student(edtIDSt.getText().toString(), edtNameSt.getText().toString(), edtPhoneNumber.getText().toString());
//                        StudentModify.updateStudent(Student);
//                        setAdapter();
//                        updateListStudent();

                        int result = studentModify.updateStudent(Student);
                        Toast.makeText(StudentList.this, "ID update: " + String.valueOf(edtIDSt.getText().toString()), Toast.LENGTH_SHORT).show();
                        if(result > 0){
//                            StudentModify.updateStudent(Student);
                            updateListStudent();
                        }
                        else {
                            Toast.makeText(StudentList.this, "Update thất bại", Toast.LENGTH_SHORT).show();
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