package com.example.movielistviewer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class primay extends AppCompatActivity {
    final private static String Url = "https://streaming-availability.p.rapidapi.com/search/basic?country=us&service=netflix&type=movie&genre=18&page=1&output_language=en&language=en";

    List<model> li;
    RecyclerView recyclerView;
    adapterForRecycle AdapterForRecycle;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primay);

        li = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleV);

        GetData getData = new GetData();

        //AdapterForRecycle.notifyDataSetChanged();


    }

    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String c ="";

            try {
                URL url;
                HttpURLConnection httpURLConnection=null;

                try {
                    url = new URL(Url);
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    InputStream i = httpURLConnection.getInputStream();
                    InputStreamReader i1 = new InputStreamReader(i);

                    int data = i1.read();

                    while (data!=-1){
                        c += (char)data;
                        data = i1.read();
                    }

                    return c;


                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    if (httpURLConnection!=null){
                        httpURLConnection.disconnect();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return c;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    model m = new model();

                    m.setName(jsonObject1.getString("title"));
                    m.setRating(jsonObject1.getString("imdbRating"));
                    m.setImg(jsonObject1.getString("posterPath"));

                    li.add(m);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            putData( li);
        }
    }

    private void putData(List<model> li){
        AdapterForRecycle = new adapterForRecycle(li,this);
        recyclerView.setAdapter(AdapterForRecycle);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(AdapterForRecycle);


    }
}