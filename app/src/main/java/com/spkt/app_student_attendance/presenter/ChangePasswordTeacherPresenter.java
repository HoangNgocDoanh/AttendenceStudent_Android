package com.spkt.app_student_attendance.presenter;

import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.spkt.app_student_attendance.model.ChangePasswordTeacherModel;
import com.spkt.app_student_attendance.model.IChangePasswordTeacherModel;
import com.spkt.app_student_attendance.view.IChangePasswordTeacherView;

public class ChangePasswordTeacherPresenter extends AppCompatActivity implements IChangePasswordTeacherPresenter {
    IChangePasswordTeacherModel iChangePasswordTeacherModel;
    IChangePasswordTeacherView iChangePasswordTeacherView;
    Handler handler;

    public ChangePasswordTeacherPresenter(IChangePasswordTeacherView iChangePasswordTeacherView) {
        this.iChangePasswordTeacherView = iChangePasswordTeacherView;
        handler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void checkChangePass(String id_teacher, String old_password, String new_password, IChangePasswordTeacherView iChangePasswordTeacherView) {
        iChangePasswordTeacherModel = new ChangePasswordTeacherModel(id_teacher, old_password, new_password, iChangePasswordTeacherView);
        iChangePasswordTeacherModel.Changepasswordteacher(id_teacher, old_password, new_password, iChangePasswordTeacherView);
    }

}
