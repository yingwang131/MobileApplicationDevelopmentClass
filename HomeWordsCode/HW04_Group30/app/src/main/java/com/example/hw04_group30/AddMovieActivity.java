package com.example.hw04_group30;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public abstract class AddMovieActivity extends Activity implements DatePickerDialog.OnDateSetListener {
    private EditText name;
    private EditText description;
    private Spinner genre;
    private EditText year;
    private EditText imdb;
    private Movie movie;
    private Button button_movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);

        movie = new Movie();
        name = (EditText) findViewById(R.id.name);
        genre = (Spinner) findViewById(R.id.spinner);
        description = (EditText) findViewById(R.id.description);
        year = (EditText) findViewById(R.id.year);
        imdb = (EditText) findViewById(R.id.imdb);
        button_movie = (Button) findViewById(R.id.button_movie);
    }


    public void addMovie(View view) {
        String expenseName = name.getText().toString();
        String category = genre.getSelectedItem().toString();
        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_empty_name), Toast.LENGTH_SHORT).show();
        } else if (genre.equals("Select Genre")) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_empty_genre), Toast.LENGTH_SHORT).show();
        } else if (description == null)
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_empty_description), Toast.LENGTH_SHORT).show();
        else if (year == null) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_empty_year), Toast.LENGTH_SHORT).show();
        } else if (imdb == null) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_empty_imdb), Toast.LENGTH_SHORT).show();
        } else {
            movie.setName(name);
            movie.setGenre(genre);
            movie.setYear(year);
            movie.setIMDB(imdb);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.MOVIE_KEY, movie);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
