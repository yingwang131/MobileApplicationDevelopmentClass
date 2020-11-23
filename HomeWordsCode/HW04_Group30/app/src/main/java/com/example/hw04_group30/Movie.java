package com.example.hw04_group30;


import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;

class Movie implements Serializable {
    private EditText name;
    private String description;
    private String genre;
    private EditText year;
    private EditText imdb;

    public EditText getName() {
        return name;
    }

    public void setName(EditText name) {
        this.name = name;
    }

    public String getGenre() {

        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditText getYear() {
        return year;
    }

    public void setYear(EditText year) {
        this.year = year;
    }

    public EditText getIMDB() {
        return imdb;
    }

    public void setIMDB(EditText imdb) {
        this.imdb = imdb;
    }

    public void setGenre(Spinner genre) {
    }
}
