package com.example.hm06fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;


public class FragmentAdd extends Fragment {

    ImageView imageView_MyAvatar;
    EditText editText_FirstName;
    EditText editText_LastName;
    EditText editText_StudentIdD;
    RadioGroup radioGroup_Department;
    RadioButton radioButton;
    Button button_save;

    private View view;

    private int imgUrl;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_layout
                , container, false);
        initiateUI(view);
        registerListener(view);
        this.view = view;
        return view;
    }

    private void registerListener(final View view) {

        // let's select the avatar first

        imageView_MyAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectAvatarIntent = new Intent(getActivity(), SelectActivity.class);
                startActivityForResult(selectAvatarIntent, Constants.AVATAR_REQ_CODE);
            }
        });


        radioGroup_Department.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                radioButton = view.findViewById(checkedId);
            }

        });

        //When the user click the save button we should save the data

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = editText_FirstName.getText().toString();
                String lastName = editText_LastName.getText().toString();
                String id = editText_StudentIdD.getText().toString();
                String department = radioButton.getText().toString();

                Student student = new Student(Integer.valueOf(id), firstName, lastName, imgUrl, department);
                //store into shared preferences
                persist(student);
                //replace fragment
                FragmentManager fm = getFragmentManager();
                FragmentTransaction tx = fm.beginTransaction();
                tx.replace(R.id.main_fragment, new FragmentDisplay());
                tx.addToBackStack(null);
                tx.commit();
            }

        });
    }

    /**
     * store the object into shared preferences
     *
     * @param student
     */
    private void persist(Student student) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("student", JSON.toJSONString(student));
        editor.commit();
    }

    private void initiateUI(View view) {
        imageView_MyAvatar = view.findViewById(R.id.imageView_MyAvatar);
        editText_FirstName = view.findViewById(R.id.editText_FirstName);
        editText_LastName = view.findViewById(R.id.editText_LastName);
        editText_StudentIdD = view.findViewById(R.id.editText_StudentID);
        radioGroup_Department = view.findViewById(R.id.radioGroup_Department);
        button_save = view.findViewById(R.id.button_Save);
        radioButton = view.findViewById(Constants.map.get("CS"));
        radioButton.setChecked(true);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String studentJson = sharedPreferences.getString("student", "");
        if (!"".equals(studentJson)) {
            Student student = JSON.parseObject(studentJson, Student.class);

            imageView_MyAvatar.setImageResource(student.getImageUrl());


            editText_FirstName.setText(student.getFirstName());

            editText_LastName.setText(student.getLastName());


            editText_StudentIdD.setText(student.getId() + "");

            radioGroup_Department.clearCheck();
            radioButton = view.findViewById(Constants.map.get(student.getDepartment()));
            radioButton.setChecked(true);
            imgUrl = student.getImageUrl();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Constants.RES_AVATAR == resultCode) {
            imgUrl = data.getIntExtra(Constants.AVATAR_SRC, R.drawable.avatar_f_1);
            imageView_MyAvatar.setImageResource(imgUrl);
        }
    }
}
