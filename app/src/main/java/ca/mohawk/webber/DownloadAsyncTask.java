package ca.mohawk.webber;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<String, Void, String> {
    public static final String TAG = "==DownloadAsyncTask==";

    /**
     * Download data from the supplied URL, catch exceptions
     *
     * @param params - first parameter is the URL
     * @return a string that concatenates all of the output together, null on failure
     */
    @Override
    protected String doInBackground(String... params) {
        Log.d(TAG, "Starting Background Task");
        StringBuilder results = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            String line;
            // Open the Connection - GET is the default setRequestMethod
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            // Read the response
            int statusCode = conn.getResponseCode();
            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(
                        conn.getInputStream());
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream,
                                "UTF-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    results.append(line);
                }
            }
            Log.d(TAG, "Data received = " + results.length());
            Log.d(TAG, "Response Code: " + statusCode);

        } catch (IOException ex) {
            Log.d(TAG, "Caught Exception: " + ex);
        }
        return results.toString();
    }

    /**
     * After download has completed associate, parse results
     * @param result - do nothing if results == null
     */
    protected void onPostExecute(String result) {
        MovieList movieList = null;
        if (result == null) {
            Log.d(TAG, "no results");
        } else {
            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
            Gson gson = new Gson();
            movieList = gson.fromJson(jsonObject.get("Search"), MovieList.class);
        }
        // fetch the current activity so we can lookup the ListView
        Activity currentActivity = MainActivity.getCurrentActivity();
        ListView lv = currentActivity.findViewById(R.id.list);
        if (movieList != null) {
            // if we populated fairlist then connect the adapter
            ArrayAdapter<Movie> adapter =
                    new ArrayAdapter<>(currentActivity,
                            android.R.layout.simple_list_item_1, movieList);
            lv.setAdapter(adapter);
        } else {
            // clear the list
            lv.setAdapter(null);
        }
    }

}


