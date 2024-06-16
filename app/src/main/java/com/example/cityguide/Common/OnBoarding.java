package com.example.cityguide.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cityguide.HelperClasses.SliderAdapter;
import com.example.cityguide.R;
import com.example.cityguide.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dots_Layout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted, skipBTN, nextBTN;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_boarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Hooks
        viewPager = findViewById(R.id.slider);
        dots_Layout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);
        skipBTN = findViewById(R.id.skip_btn);
        nextBTN = findViewById(R.id.next_btn);

        //Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);

    }

    private void addDots(int position) {
        dots = new TextView[4];
        dots_Layout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dots_Layout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//            dots[position].setTextAppearance(R.style.B);

        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
            currentPos = position;

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                nextBTN.setVisibility(View.VISIBLE);
                skipBTN.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                nextBTN.setVisibility(View.VISIBLE);
                skipBTN.setVisibility(View.VISIBLE);
            } else if (position == 2) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                nextBTN.setVisibility(View.VISIBLE);
                skipBTN.setVisibility(View.VISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);

                nextBTN.setVisibility(View.INVISIBLE);
                skipBTN.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}