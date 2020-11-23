package com.example.midtermweather;

import androidx.annotation.NonNull;

public class City {
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String country;

    public City() {

    }

    public City(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @NonNull
    @Override
    public String toString() {
        return this.city + "," + this.country;
    }
}
