package com.example.hm05newsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ListView listView;
//    String urlToImage, title, author, date;
//
//    public NewsActivity(String urlToImage, String title, String author, String date) {
//        this.urlToImage = urlToImage;
//        this.title = title;
//        this.author = author;
//        this.date = date;
//    }


    TextView tv_title;
    TextView tv_author;
    TextView tv_date;
    ImageView iv_urlToImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Initionalize the id
        tv_title = findViewById(R.id.tv_title);
        tv_author = findViewById(R.id.tv_author);
        tv_date = findViewById(R.id.tv_date);
        iv_urlToImage = findViewById(R.id.iv_urlToImage);
        listView = findViewById(R.id.listView_News);

        //获取从main传递过来的数据，缺啥传啥: get the data from the main activity
        String soureId = getIntent().getStringExtra("sourceId");
        String category = getIntent().getStringExtra("category");
        this.setTitle(category);
        String url = "https://newsapi.org/v2/top-headlines?sources=" + soureId + "&apiKey=c2f3d9e9c24140f59af7390a9e6cd327";
        //构造url,异步发起网络请求: get the URL
        callSourcesAPI(url);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article article = (Article) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(NewsActivity.this, WebActivity.class);
                intent.putExtra("url", article.getUrl());
                startActivity(intent);
            }
        });


    }

    /**
     * call source API to get data and fill them into listview
     */
    private void callSourcesAPI(String url) {
        if (isConnected()) {
            new GetDataAsync().execute(url);
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
    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<Article>> {
        @Override
        protected ArrayList<Article> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Article> result = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                    Log.d("hello", json);
                    JSONObject root = new JSONObject(json);
                    // under the object:status,  array of sources
                    String status = root.getString("status");
                    if ("ok".equals(status)) {//if status is ok, mean nothing happens
                        JSONArray articles = root.getJSONArray("articles");
                        for (int i = 0; i < articles.length(); i++) {
                            JSONObject articleJson = articles.getJSONObject(i);
                            Article article = new Article();
                            article.setAuthor(articleJson.getString("author"));
                            article.setTitle(articleJson.getString("title"));
                            article.setDescription(articleJson.getString("description"));
                            article.setUrl(articleJson.getString("url"));
                            article.setUrlToImage(articleJson.getString("urlToImage"));
                            article.setPublishedAt(articleJson.getString("publishedAt"));
                            article.setContent(articleJson.getString("content"));
                            result.add(article);
                        }
                    } else {//if we have some exception,we will catch them

                    }

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
            Log.d("start processing", "Before the category");

            progressDialog = new ProgressDialog(NewsActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(10);
            progressDialog.show();

            listView.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPostExecute(ArrayList<Article> result) {

            //获取请求结果，通过adapter适配到List view里头
            if (result.size() > 0) {
                progressDialog.hide();

                //2. fill data in list view
                ItemAdapter adapter = new ItemAdapter(NewsActivity.this, R.layout.source_item, result);
//                ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, data);
                listView.setAdapter(adapter);
                Log.d("demo", result.toString());
                listView.setVisibility(View.VISIBLE);

            } else {
                Log.d("demo", "empty result");
            }
        }
    }
}
