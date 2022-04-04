package ca.mohawk.webber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
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
        String uri = "http://www.omdbapi.com/?apikey=" + API_KEY + "&s=music";
        EditText authorEditText = (EditText) findViewById(R.id.searchEditText);
        String author = authorEditText.getText().toString();
        if(!author.equals("")) {
            // uri += author + "&key=" + API_KEY;
        }
        Log.d(TAG, "Start download: " + uri);
        dl.execute(uri);
    }

    public void searchByTitle(View view) {

    }
}