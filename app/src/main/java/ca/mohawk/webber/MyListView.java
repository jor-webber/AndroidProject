package ca.mohawk.webber;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MyListView<M> extends ArrayAdapter<Movie> {

    private final Activity context;
    private final ArrayList<Movie> movies;

    public MyListView(Activity context, int viewId, ArrayList<Movie> movies) {
        super(context, viewId, movies);

        this.context=context;
        this.movies=movies;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        Bitmap image = getBitmapFromUrl(movies.get(position).Poster);

        titleText.setText(movies.get(position).Title);
        imageView.setImageBitmap(image);
        subtitleText.setText(movies.get(position).Title);

        return rowView;
    }

    public Bitmap getBitmapFromUrl(String source) {
        try {
            java.net.URL url = new java.net.URL(source);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}