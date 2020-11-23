package com.example.hw04_group30;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class EditMovieActivity extends Activity implements DatePickerDialog.OnDateSetListener {
    private ArrayList<Movie> movies;
    private Movie movie;

    private Button button_SelectMovie;
    private EditText name;
    private Spinner genre;
    private EditText year;
    private EditText description;
    private EditText imdb;
    private Button button_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmovie);

        movie = new Movie();

        movie = new Movie();
        name = (EditText) findViewById(R.id.name);
        genre = (Spinner) findViewById(R.id.spinner);
        description = (EditText) findViewById(R.id.description);
        year = (EditText) findViewById(R.id.year);
        imdb = (EditText) findViewById(R.id.imdb);
        button_save = (Button) findViewById(R.id.button_save);

        if (getIntent().getExtras() != null) {
            movies = (ArrayList<Movie>) getIntent().getExtras().getSerializable(MainActivity.MOVIES_KEY);
        }

        name.setEnabled(false);
        genre.setEnabled(false);
       year.setEnabled(false);
       imdb.setEnabled(false);
        description.setEnabled(false);
        button_save.setEnabled(false);
    }

    public void selectMovie(View view) {
        if (movies != null && movies.size() != 0) {
            registerForContextMenu(view);
            openContextMenu(view);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Pick a movie");

        for (int i = 0; i < movies.size(); i++) {
            menu.add(0, i, 0, (CharSequence) movies.get(i).getName());
        }
    }


    public void cancelEdit(View view) {
        finish();
    }
}