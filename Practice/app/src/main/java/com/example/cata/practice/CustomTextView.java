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

    private final static String FONT_BEN_KRUSH = "ben_krush";
    private final static String FONT_ROMAN_ITALIC = "roman_italic";
    private final static String FONT_ROBOTO_LIGHT = "roboto_light";
    private final static String FONT_TABASSOM_NORMAL ="tabassom_normal";


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
    }

    private void selectTypeface(Context context, String fontName){

        Log.v("FONT:", fontName);
        switch (fontName){
            case FONT_BEN_KRUSH:
                setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/ben-krush.ttf"));
                break;

            case FONT_ROMAN_ITALIC:
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roman_italic.ttf"));
                break;

            case FONT_ROBOTO_LIGHT:
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
                break;

            case FONT_TABASSOM_NORMAL:
                setTypeface(Typeface.createFromAsset(context.getAssets(),  "fonts/tabassom_normal.ttf"));
                break;

            default:
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/tabassom_normal.ttf"));
        }

    }
}
