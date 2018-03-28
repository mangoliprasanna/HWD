package com.example.mango.hwd;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mango on 28-03-2018.
 */
public class TweeterLoader extends AsyncTaskLoader<List<Tweet>> {

    public TweeterLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Tweet> loadInBackground() {
        Log.w("loadInBackground  : ", "Called");
        FetchData obj = new FetchData();
        String json_Data = obj.getData("https://twitter-133.herokuapp.com/");
        List<Tweet> finalList = new ArrayList<>();
        try {
            JSONArray list_data = new JSONArray(json_Data);

            for(int i = 0; i < list_data.length(); i++)
            {
                JSONObject root = list_data.getJSONObject(i);
                finalList.add(new Tweet(
                        root.getString("name"),
                        root.getString("location"),
                        root.getString("profile"),
                        "@" + root.getString("screen_name"),
                        root.getString("text"),
                        root.getInt("likes"),
                        root.getString("image")
                ));
            }
        } catch (JSONException e) {
            Log.w("Error", "Error While Converting to Tweet");
            e.printStackTrace();
        }
        return finalList;
    }
}
