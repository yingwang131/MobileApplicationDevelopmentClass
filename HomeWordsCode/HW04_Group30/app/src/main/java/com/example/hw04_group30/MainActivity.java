package com.example.hw04_group30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import android.os.Bundle;

/**
 * Group 30
 * Heidi Hao
 * Ying Wang
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private Intent intent;

    public static final int ADD_MOVIE_KEY = 1;
    public static final int EDIT_MOVIE_KEY = 2;
    public static final int DELETE_MOVIE_KEY = 3;
    //public static final String SHOW_MOVIE_BY_YEAR_KEY = "SHOW_MOVIE_BY_YEAR_KEY";
    //public static final String SHOW_MOVIE_BY_RATING_KEY = "SHOW_MOVIE_BY_RATING_KEY";
    public static final String MOVIES_KEY = "MOVIES";
    public static final String MOVIE_KEY = "MOVIE";
    private Button add_movie, edit, delete_movie,show_list,show_list_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<Movie>();
        add_movie = (Button)findViewById(R.id.add_movie);
        edit = (Button)findViewById(R.id.edit);
        delete_movie = (Button)findViewById(R.id.delete_movie);
        checkAndDisableButtons();
    }

    private void checkAndDisableButtons() {
        if (movies.isEmpty()) {
            add_movie.setEnabled(false);
            edit.setEnabled(false);
            delete_movie.setEnabled(false);
            show_list.setEnabled(false);
            show_list_rating.setEnabled(false);

        } else {
            add_movie.setEnabled(true);
            edit.setEnabled(true);
            delete_movie.setEnabled(true);
            show_list.setEnabled(true);
            show_list_rating.setEnabled(true);
        }
    }
    public void addMovie(View view) {
        intent = new Intent(MainActivity.this, AddMovieActivity.class);
        startActivityForResult(intent, ADD_MOVIE_KEY);
    }
    public void editMovie(View view) {
        intent = new Intent(MainActivity.this, EditMovieActivity.class);
        intent.putExtra(MOVIES_KEY,movies);
        startActivityForResult(intent, EDIT_MOVIE_KEY);
    }

    public void deleteMovie(View view) {
        intent = new Intent(MainActivity.this, DeleteMovieActivity.class);
        intent.putExtra(MOVIES_KEY,movies);
        startActivityForResult(intent, DELETE_MOVIE_KEY);
    }

    public void displayListYear(View view) {
        intent = new Intent(MainActivity.this, DisplayListYearActivity.class);
        intent.putExtra(MOVIES_KEY,movies);
        startActivity(intent);
    }
    public void displayListRating(View view) {
        intent = new Intent(MainActivity.this, DisplayListRatingActivity.class);
        intent.putExtra(MOVIES_KEY,movies);
        startActivity(intent);
    }

    public void finish(View view) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Movie movie;

        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_MOVIE_KEY) {
                movie = (Movie) data.getExtras().getSerializable(MOVIE_KEY);
                movies.add(movie);
                checkAndDisableButtons();
            } else if (requestCode == EDIT_MOVIE_KEY) {
                movies = (ArrayList<Movie>) data.getExtras().getSerializable(MOVIES_KEY);
            } else if (requestCode == DELETE_MOVIE_KEY) {
                movies = (ArrayList<Movie>) data.getExtras().getSerializable(MOVIES_KEY);
                checkAndDisableButtons();
            }
        } else if (resultCode == RESULT_CANCELED) {

        }
    }



}
