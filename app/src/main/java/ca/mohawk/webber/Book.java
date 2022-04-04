package ca.mohawk.webber;

import android.media.Image;

import androidx.annotation.NonNull;

public class Book {
    public String _id;
    public String Author;
    public String Title;
    public String Year;
    public Image Image;
    public String Description;

    @NonNull
    @Override
    public String toString() {
        return "This is to String Hello there";
    }
}
