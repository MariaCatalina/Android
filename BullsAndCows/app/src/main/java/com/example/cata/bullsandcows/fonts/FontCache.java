package com.example.cata.bullsandcows.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

/**
 * Created by Cata on 11/29/2016.
 */

public class FontCache {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    private static final String TAG = FontCache.class.toString();

    public static Typeface getTypeface(String fontName, Context context) {
        Typeface typeface = fontCache.get(fontName);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                Log.i(TAG, "getTypeface: " + e);
            }
        }

        fontCache.put(fontName, typeface);

        return typeface;
    }

}
