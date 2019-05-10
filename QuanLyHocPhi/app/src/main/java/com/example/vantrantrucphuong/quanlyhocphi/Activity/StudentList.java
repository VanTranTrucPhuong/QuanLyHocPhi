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
            dialog.setTitle("Thêm mới sinh viên");
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
                    Student student = new Student(edtIDSt.getText().toString(),edtNameSt.getText().toString(), edtPhoneNumber.getText().toString());
                    boolean flag = true;
                    for(int i = 0; i < studentList.size(); i++){
                        if(student.getStudent_id().equals(studentList.get(i).getStudent_id())){
                            flag = false;
                        }
                    }
                    if (flag == false) {
                        Toast.makeText(StudentList.this, "Mã sinh viên này đã tồn tại !!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(student != null){
                            studentModify.addStudent(student);
                        }
                        updateListStudent();
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
        final String id = studentItem.getStudent_id();

        Toast.makeText(this, (lvStudent.getItemAtPosition(info.position)).toString(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder builder = new AlertDialog.Builder(StudentList.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int result = studentModify.deleteStudent(id);
                        if(result>0){
                            Toast.makeText(getApplicationContext(),"Xóa thành công", Toast.LENGTH_SHORT).show();
                            updateListStudent();
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

                edtIDSt.setEnabled(false);

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