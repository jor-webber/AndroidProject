package ca.mohawk.webber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "==MainActivity==";
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
}