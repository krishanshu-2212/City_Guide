package com.example.cityguide.HelperClasses.HomeAdapter;


import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {

    int image;
    String title;
    GradientDrawable backgroundGradient;

    public CategoriesHelperClass(int image, String title, GradientDrawable backgroundGradient) {
        this.image = image;
        this.title = title;
        this.backgroundGradient = backgroundGradient;


    }

    public int getImage() {

        return image;
    }

    public String getTitle() {

        return title;
    }

    public GradientDrawable getbackgroundGradient() {

        return backgroundGradient;
    }

}
