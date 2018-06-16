package misha.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import misha.mytest.adapters.ItemsAdapter;
import misha.mytest.dataModels.Item;
import misha.mytest.utils.ItemsLoaderTask;
import misha.mytest.utils.Util;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerView.Adapter recyclerAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        loadData();

    }
    private void loadData(){

        new ItemsLoaderTask(){// calling my custom AsyncTask to load and parse the items

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE); // showing a ProgressBar before loading data from the network
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                //after the load is done I put the data in a RecyclerView
                if(items != null) {
                    recyclerAdapter = new ItemsAdapter(MainActivity.this, items);
                    recycler.setAdapter(recyclerAdapter);
                }
                //after the data is loaded and set, I command the progress bar to disappear!
                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }
}
