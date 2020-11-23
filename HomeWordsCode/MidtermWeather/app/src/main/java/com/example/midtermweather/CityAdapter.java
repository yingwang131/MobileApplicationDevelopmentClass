package com.example.midtermweather;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    private List<City> list;
    LayoutInflater inflater;

    public CityAdapter(Context context, List<City> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item, null);
            viewHolder = new ViewHolder();
            viewHolder.city = convertView.findViewById(R.id.tv_city);
            viewHolder.country = convertView.findViewById(R.id.tv_country);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.city.setText(list.get(position).getCity());
        viewHolder.country.setText(list.get(position).getCountry());

        return convertView;
    }

    @Override
    public Object getItem(int i) {
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    private class ViewHolder {
        public TextView city;
        public TextView country;
    }

}
