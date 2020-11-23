package com.example.midtermweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    TextView temperature;
    TextView temperatureNum;
    TextView temperatureMax;
    TextView temperatureMaxNum;
    TextView temperatureMin;
    TextView getTemperatureMinNum;
    TextView temperatureDes;
    TextView temperatureDesNum;
    TextView humidity;
    TextView humidityNum;
    TextView windSpeed;
    TextView windSpeedNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        String url = "http://api.openweathermap.org/data/2.5/weather?" +
                "q=" + city + "&appid=6a5032edcfba30fe9dd0d12bc755a466";
        new GetDataAsync().execute(url);

        temperature = findViewById(R.id.temp);
        temperatureNum = findViewById(R.id.temp1);
        temperatureMax = findViewById(R.id.temp_max);
        temperatureMaxNum = findViewById(R.id.temp_max1);
        temperatureMin = findViewById(R.id.tmp_min);
        getTemperatureMinNum = findViewById(R.id.tmp_min1);
        temperatureDes = findViewById(R.id.description);
        temperatureDesNum = findViewById(R.id.description1);
        humidity = findViewById(R.id.humidity);
        humidityNum = findViewById(R.id.humidity1);
        windSpeed = findViewById(R.id.wind);
    }


    /**
     * make sure the connection works
     *
     * @return
     */
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    public class GetDataAsync extends AsyncTask<String, Void, ArrayList> {

        @Override
        protected ArrayList doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream is = connection.getInputStream();
                    int size = is.available();

                    byte[] buffer = new byte[size];

                    is.read(buffer);

                    is.close();

                    String json = new String(buffer, "UTF-8");

                    JSONObject.parseObject(json);
                }
                return null;
            } catch (Exception e) {

            }
            return null;
        }
    }
}