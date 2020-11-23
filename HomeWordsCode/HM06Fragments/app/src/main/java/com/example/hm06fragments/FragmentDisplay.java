package com.example.hm06fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;

public class FragmentDisplay extends Fragment {


    ImageView imageView;
    TextView tvStudentName;
    TextView tvStudentId;
    TextView tvDepartment;
    Button btnEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display_profile
                , container, false);

        initiateUI(view);
        registerListeners();

        return view;
    }

    /**
     * register listeners on different components
     */
    private void registerListeners() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //replace fragment
                FragmentManager fm = getFragmentManager();
                FragmentTransaction tx = fm.beginTransaction();
                tx.replace(R.id.main_fragment, new FragmentAdd());
                tx.addToBackStack(null);
                tx.commit();
            }
        });
    }

    /**
     * initiate UI of this fragment
     *
     * @param view
     */
    private void initiateUI(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String studentJson = sharedPreferences.getString("student", "");
        if (!"".equals(studentJson)) {
            Student student = JSON.parseObject(studentJson, Student.class);

            imageView = view.findViewById(R.id.image_view_display);
            imageView.setImageResource(student.getImageUrl());

            tvStudentName = view.findViewById(R.id.tv_display_name);
            tvStudentName.setText(student.getFirstName() + " " + student.getLastName());

            tvStudentId = view.findViewById(R.id.tv_student_id);
            tvStudentId.setText(student.getId() + "");

            tvDepartment = view.findViewById(R.id.tv_department);
            tvDepartment.setText(student.getDepartment());

            btnEdit = view.findViewById(R.id.btn_edit);


        }

    }

}
