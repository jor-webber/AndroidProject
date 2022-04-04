package ca.mohawk.webber;

import androidx.annotation.NonNull;

public class Movie {
    public String Title;
    public String Year;
    public String imdbID;
    public String Type;
    public String Poster;

    @NonNull
    @Override
    public String toString() {
        return Title + " " + Year + " " + imdbID + " " + Type;
    }
}

