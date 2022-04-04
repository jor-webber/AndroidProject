package ca.mohawk.webber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = "==MainActivity==";
    public static final String API_KEY = "f230a5eb";
    /** single instance of current activity **/
    private static Activity currentActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fm.beginTransaction();
        Fragment topFrag = new SearchFragment();
        Fragment bottomFrag = new ListFragment();
        fragTransaction.replace(R.id.topFrameLayout, topFrag);
        fragTransaction.replace(R.id.bottomFrameLayout, bottomFrag);
        fragTransaction.commit();
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public void searchByAuthor(View view) {
        DownloadAsyncTask dl = new DownloadAsyncTask();
        String uri = "http://www.omdbapi.com/?apikey=" + API_KEY + "&s=";
        EditText searchEditText = (EditText) findViewById(R.id.searchEditText);
        String searchTerm = searchEditText.getText().toString();
        if(!searchTerm.equals("")) {
            uri += searchTerm;
        }
        Log.d(TAG, "Start download: " + uri);

        dl.execute(uri);

        ListView listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(MainActivity.getCurrentActivity(), SingleMovieActivity.class);
        Movie movie =(Movie)adapterView.getItemAtPosition(i);
        intent.putExtra("title", movie.Title);
        intent.putExtra("year", movie.Year);
        intent.putExtra("image", movie.Poster);
        startActivity(intent);
    }
}