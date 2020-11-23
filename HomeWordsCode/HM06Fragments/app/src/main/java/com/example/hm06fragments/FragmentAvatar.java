package com.example.hm06fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentAvatar extends Fragment {

    private ImageView imageView;


    private int imgSrc;
    private int imgId;


    public FragmentAvatar() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_avatar_layout
                , container, false);
        imageView = view.findViewWithTag("avatar");
        build();
        registerListener();
        return view;


    }

    /**
     * register listeners for components
     */
    private void registerListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.AVATAR_SRC, imgSrc);
                getActivity().setResult(Constants.RES_AVATAR, intent);
                getActivity().finish();
            }
        });
    }


    public ImageView getImageView() {
        return imageView;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void build() {
        imageView.setImageResource(imgSrc);
        imageView.setId(Integer.valueOf(imgId));
    }


}
