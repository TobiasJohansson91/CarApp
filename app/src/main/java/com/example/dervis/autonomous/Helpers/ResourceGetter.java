package com.example.dervis.autonomous.Helpers;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.dervis.autonomous.R;

public class ResourceGetter {

    public static Drawable getDrawable(int id){
        return ContextCompat.getDrawable(AppContextGetter.getContext(), id);
    }

    public static String getString(int id){
        return AppContextGetter.getContext().getString(id);
    }
}
