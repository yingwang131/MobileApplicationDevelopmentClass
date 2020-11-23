package com.example.hm05newsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ProgressDialog progressDialog;
    ListAdapter adapter;

    ArrayList<NewsActivity> newsactivity = new ArrayList<>();

    /**
     * key is name, value is sources object
     */
    Map<String, Sources> sourcesMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        // 1. get data
        callSourcesAPI();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("sourceId", sourcesMap.get(name).id);
                intent.putExtra("category",name);
//                Log.d("hello", name);
                startActivity(intent);

            }
        });

    }

    /**
     * call source API to get data and fill them into listview and Implement the AlertDialog
     */
    private void callSourcesAPI() {
        if (isConnected()) {
            Toast.makeText(MainActivity.this, "Internet Present", Toast.LENGTH_SHORT).show();
            new GetDataAsync().execute("https://newsapi.org/v2/sources?apiKey=c2f3d9e9c24140f59af7390a9e6cd327");
        } else {
            Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }
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

    /**
     * Implement the AsyncTask task and parsing the Json data
     */
    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<Sources>> {
        @Override
        protected ArrayList<Sources> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Sources> result = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                    JSONObject root = new JSONObject(json);
                    // under the object:status,  array of sources
                    String status = root.getString("status");
                    if ("ok".equals(status)){
                        JSONArray sources = root.getJSONArray("sources");
                        for (int i = 0; i < sources.length(); i++) {
                            JSONObject sourcesJson = sources.getJSONObject(i);
                            Sources sourceData = new Sources();
                            sourceData.id = sourcesJson.getString("id");
                            sourceData.name = sourcesJson.getString("name");
                            sourceData.description = sourcesJson.getString("description");
                            sourceData.url = sourcesJson.getString("url");
                            sourceData.category = sourcesJson.getString("category");
                            sourceData.language = sourcesJson.getString("language");
                            sourceData.country = sourcesJson.getString("country");
                            result.add(sourceData);
                        }
                    } else{

                    }

                    /*
                    "id": "abc-news",
                      "name": "ABC News",
                      "description": "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                      "url": "https://abcnews.go.com",
                      "category": "general",
                      "language": "en",
                      "country": "us"
                     */

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(10);
            progressDialog.show();

            listView.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPostExecute(ArrayList<Sources> result) {
            if (result.size() > 0) {
                progressDialog.hide();
                listView.setVisibility(View.VISIBLE);
                List<String> data = new ArrayList<>();
                for (Sources source :
                        result) {
                    sourcesMap.put(source.name, source);
                    data.add(source.name);
                }
                //2. fill data in list view
                ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, data);
                listView.setAdapter(adapter);
                Log.d("demo", result.toString());
            } else {
                Log.d("demo", "empty result");
            }

        }
    }


}
