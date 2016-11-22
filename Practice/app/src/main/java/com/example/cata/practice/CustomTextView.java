package com.example.cata.practice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Cata on 11/21/2016.
 */

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/ben-krush.ttf"));
}

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        applyCustomFont(context, attrs);

    }

    private void applyCustomFont(Context context, AttributeSet attrs){
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_font);

        selectTypeface(context, fontName);
        //setTypeface(customFont);
    }

    private void selectTypeface(Context context, String fontName){

        Log.v("FONT:", fontName);
        switch (fontName){
            case "ben_krush":
                setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/ben-krush.ttf"));
                break;
            case "roman_italic":
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roman_italic.ttf"));
                break;

            case "roboto_light":
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
                break;

            case "tabassom_normal":
                setTypeface(Typeface.createFromAsset(context.getAssets(),  "fonts/tabassom_normal.ttf"));
                break;

            default:
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/tabassom_normal.ttf"));
        }


//        if (fontName.contains(context.getString(R.string.ben_krush)))
//            setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/ben-krush.ttf"));
//        else
//            setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roman_italic.ttf"));

    }
}
