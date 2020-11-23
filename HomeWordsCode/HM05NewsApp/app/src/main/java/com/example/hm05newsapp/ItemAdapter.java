package com.example.hm05newsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Article> {


    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    public ItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Article article = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.source_item, parent, false);
        }

        TextView tv_title = convertView.findViewById(R.id.tv_title);
        TextView tv_author = convertView.findViewById(R.id.tv_author);
        TextView tv_date = convertView.findViewById(R.id.tv_date);
        ImageView iv_urlToImage = convertView.findViewById(R.id.iv_urlToImage);

        tv_title.setText(article.getTitle());
        tv_author.setText(article.getAuthor());
        tv_date.setText(article.getPublishedAt());
//        iv_urlToImage.setImageURI(Uri.parse(article.getUrlToImage()));
        Picasso.get().load(article.getUrlToImage()).resize(90, 90).into(iv_urlToImage);
        return convertView;

    }


}
