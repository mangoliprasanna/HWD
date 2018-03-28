package com.example.mango.hwd;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tweet>> {

    ListView tweetList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tweetList = (ListView) findViewById(R.id.tweetList);

        getLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<List<Tweet>> onCreateLoader(int i, Bundle bundle) {
        return new TweeterLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Tweet>> loader, List<Tweet> tweets) {
        TweetAdapter adapter = new TweetAdapter(this, 0, tweets);
        tweetList.setAdapter(adapter);
        Log.w("Name", tweets.get(0).getName());
    }
    @Override
    public void onLoaderReset(Loader<List<Tweet>> loader) {

    }
}


