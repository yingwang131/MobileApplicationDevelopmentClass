package com.example.hm06fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SelectActivity extends AppCompatActivity {

    LinearLayout maleContainer;
    LinearLayout femaleContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        setTitle("My Profile");
        initiateUI();
        fillMaleAvatar();
        fillFemaleAvatar();

//        findViewById(R.id.imageView_Female1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent female1 = new Intent();
//                int image_number = 1;
//                female1.putExtra(Constants.AVATAR_SRC, image_number);
//                setResult(RESULT_OK, female1);
//                finish();
//            }
//        });
//
//        findViewById(R.id.imageView_Female2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent female2 = new Intent();
//                int image_number = 2;
//                female2.putExtra(Constants.AVATAR_SRC, image_number);
//                setResult(RESULT_OK, female2);
//                finish();
//            }
//        });
//
//        findViewById(R.id.imageView_Female3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent female3 = new Intent();
//                int image_number = 3;
//                female3.putExtra(Constants.AVATAR_SRC, image_number);
//                setResult(RESULT_OK, female3);
//                finish();
//            }
//        });
//
//        findViewById(R.id.imageView_Male1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent male1 = new Intent();
//                int image_number = 4;
//                male1.putExtra(Constants.AVATAR_SRC, image_number);
//                setResult(RESULT_OK, male1);
//                finish();
//            }
//        });
//
//        findViewById(R.id.imageView_Male2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent male2 = new Intent();
//                int image_number = 5;
//                male2.putExtra(Constants.AVATAR_SRC, image_number);
//                setResult(RESULT_OK, male2);
//                finish();
//            }
//        });
//
//        findViewById(R.id.imageView_Male3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent male3 = new Intent();
//                int image_number = 6;
//                male3.putExtra(Constants.AVATAR_SRC, image_number);
//                setResult(RESULT_OK, male3);
//                finish();
//            }
//        });

    }

    private void fillFemaleAvatar() {

        int idx = 1;
        for (Avatar avatar : Constants.femaleAvatars) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            Fragment female = new FragmentAvatar();
            ((FragmentAvatar) female).setImgId(avatar.avatarId);
            ((FragmentAvatar) female).setImgSrc(avatar.avatarSrc);
            tx.add(R.id.female_container, female, "FEMALE" + idx);
            idx++;
            tx.commit();
        }

    }

    private void fillMaleAvatar() {
        int idx = 1;
        for (Avatar avatar : Constants.maleAvatars) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            Fragment male = new FragmentAvatar();
            ((FragmentAvatar) male).setImgId(avatar.avatarId);
            ((FragmentAvatar) male).setImgSrc(avatar.avatarSrc);
            tx.add(R.id.male_container, male, "MALE" + idx);
            idx++;
            tx.commit();
        }
    }

    private void initiateUI() {
        maleContainer = findViewById(R.id.male_container);
        femaleContainer = findViewById(R.id.female_container);
    }

}
