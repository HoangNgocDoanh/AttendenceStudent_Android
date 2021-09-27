package com.spkt.app_student_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.adapter.StudentAdapter;
import com.spkt.app_student_attendance.model.IPConfigModel;
import com.spkt.app_student_attendance.model.StudentModel;
import com.spkt.app_student_attendance.presenter.IStudentListPresenter;
import com.spkt.app_student_attendance.presenter.StudentListPresenter;
import com.spkt.app_student_attendance.view.IStudentListView;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity implements IStudentListView,View.OnClickListener{
    private IPConfigModel ipConfigModel = new IPConfigModel();
    private EditText editText;
    private ImageView img_btn_back;
    private String class_id;
    //MVP
    private ListView list;
    private String id_student,class_name;
    private IStudentListPresenter iStudentListPresenter = new StudentListPresenter(this);
    ArrayList<StudentModel> List_Student;
    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);
        AnhXa();
        LoadClassForStudent();

        // Funcion on Click list view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel student_model = (StudentModel) studentAdapter.getItem(position);
                //Do in Click Class
                Intent intent = new Intent(view.getContext() , DeatailStudentActivity.class);
                intent.putExtra("STUDENT_ID", student_model.getStudent_id());
                intent.putExtra("STUDENT_NAME", student_model.getStudent_name());
                intent.putExtra("STUDENT_EMAIL", student_model.getStudent_mail());
                intent.putExtra("STUDENT_PHONE", student_model.getStudent_phone());
                intent.putExtra("STUDENT_DOB", student_model.getStudent_birth());
                intent.putExtra("STUDENT_CLASS", class_name);
                intent.putExtra("checklayout", "1");
                intent.putExtra("ID_CLASS", class_id);
                intent.putExtra("ID_STUDENT", id_student);
                // Truyền Student
                startActivity(intent);
            }
        });
        img_btn_back.setOnClickListener(this);
    }

    public void LoadClassForStudent() {
        Intent intent = getIntent();
        class_id =intent.getStringExtra("ID_CLASS");
        id_student =intent.getStringExtra("ID_STUDENT");
        iStudentListPresenter.doLoadListStudent(id_student, this);

    }
    public void AnhXa() {
        list = findViewById(R.id.recyclerviewStudent);
        img_btn_back = (ImageView) findViewById(R.id.img_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back:
                Intent intent1 = new Intent(StudentListActivity.this, StudentActivity.class);
                intent1.putExtra("ID_STUDENT", id_student);
                startActivity(intent1);
                finish();
                break;
        }
    }

    @Override
    public void onListClassStudentResult(ArrayList<StudentModel> List_Student) {
        studentAdapter = new StudentAdapter(this,List_Student);
        list.setAdapter(studentAdapter);
    }
}
