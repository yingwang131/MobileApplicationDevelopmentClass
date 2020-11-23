package com.example.hm06fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final String AVATAR_SRC = "img";
    public static final int RES_AVATAR = 200;
    public static final int AVATAR_REQ_CODE = 5;


    public static List<Avatar> femaleAvatars = new ArrayList<>();

    static {
        femaleAvatars.add(new Avatar(R.string.f1, R.drawable.avatar_f_1));
        femaleAvatars.add(new Avatar(R.string.f2, R.drawable.avatar_f_2));
        femaleAvatars.add(new Avatar(R.string.f3, R.drawable.avatar_f_3));
    }

    public static List<Avatar> maleAvatars = new ArrayList<>();

    static {
        maleAvatars.add(new Avatar(R.string.m1, R.drawable.avatar_m_1));
        maleAvatars.add(new Avatar(R.string.m2, R.drawable.avatar_m_2));
        maleAvatars.add(new Avatar(R.string.m3, R.drawable.avatar_m_3));
    }

    public static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("CS", R.id.radioButton_CS);
        map.put("SIS", R.id.radioButton_SIS);
        map.put("BIO", R.id.radioButton_BIO);
        map.put("OTHER", R.id.radioButton_Other);
    }
}
