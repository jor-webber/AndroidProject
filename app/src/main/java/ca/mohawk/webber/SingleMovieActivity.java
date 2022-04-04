package ca.mohawk.webber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SingleMovieActivity extends AppCompatActivity {
    private final String TAG = "==SingleMovieActivity==";
    private static SingleMovieActivity currentActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        currentActivity = this;

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String year = intent.getStringExtra("year");
        String image = intent.getStringExtra("image");

        Log.d(TAG, "Received from intent: " + title + " " + year + " " + image);

        TextView titleTextView = findViewById(R.id.titleText);
        TextView yearTextView = findViewById(R.id.yearText);
        ImageView imageView = findViewById(R.id.imageView);



        titleTextView.setText(title);
        yearTextView.setText(year);
        DownloadImageTask dl = new DownloadImageTask();
        dl.execute(image);
    }

    public static SingleMovieActivity getCurrentActivity() {
        return currentActivity;
    }
}