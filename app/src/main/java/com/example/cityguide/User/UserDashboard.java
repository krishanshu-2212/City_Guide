package com.example.cityguide.User;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.Common.LoginSignup.RetailerStartUpScreen;
import com.example.cityguide.Common.LoginSignup.Retailer_Login;
import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.MostViewedAdpater;
import com.example.cityguide.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.cityguide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler, mostViewRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.main);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);



        navigationDrawer();

        //Recycler View Function Call
        featuredRecycler();
        mostViewRecycler();
        categoriesRecycler();

    }



    private int selectedNavItem = R.id.nav_home; // Default selected item is R.id.nav_home


    private void navigationDrawer() {
        // Your other navigation drawer setup code...

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(selectedNavItem); // Set the previously selected item

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_all_categories) {
            startActivity(new Intent(getApplicationContext(), AllCategories.class));
        }
        // Handle other menu items...
        else if (itemId == R.id.nav_search) {
            startActivity(new Intent(getApplicationContext(), DrawerSearch.class));
        }



        drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after item selection
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(selectedNavItem); // Restore the selected item when activity resumes
    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.banner_background_light));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }




    //Recycler View Functions
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonalds_img, "Mcdonald's", "McDonald's Corporation is an American multinational fast food chain, founded in 1940."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.burgerking_img, "Burger King", "Burger King Corporation is an American multinational chain of hamburger fast food restaurants."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.subway_img, "Subway", "Subway, is an American multinational fast food restaurant franchise that specializes in submarine sandwiches, wraps, salads, and drinks."));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    private void mostViewRecycler() {
        mostViewRecycler.setHasFixedSize(true);
        mostViewRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> MSLocations = new ArrayList<>();

        MSLocations.add(new MostViewedHelperClass(R.drawable.mcdonalds_img, "Mcdonald's", "McDonald's Corporation is an American multinational fast food chain, founded in 1940."));
        MSLocations.add(new MostViewedHelperClass(R.drawable.burgerking_img, "Burger King", "Burger King Corporation is an American multinational chain of hamburger fast food restaurants."));
        MSLocations.add(new MostViewedHelperClass(R.drawable.subway_img, "Subway", "Subway, is an American multinational fast food restaurant franchise that specializes in submarine sandwiches, wraps, salads, and drinks."));

        adapter = new MostViewedAdpater(MSLocations);
        mostViewRecycler.setAdapter(adapter);

    }

    private void categoriesRecycler() {
        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});

        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> CLocations = new ArrayList<>();

        CLocations.add(new CategoriesHelperClass(R.drawable.education_img, "Education", gradient1));
        CLocations.add(new CategoriesHelperClass(R.drawable.hospital_img, "Hospital", gradient2));
        CLocations.add(new CategoriesHelperClass(R.drawable.restaurants_img, "Restaurants", gradient3));
        CLocations.add(new CategoriesHelperClass(R.drawable.shopping_img, "Shopping", gradient4));

        adapter = new CategoriesAdapter(CLocations);
        categoriesRecycler.setAdapter(adapter);


    }
// End of Recycler View

    //Normal Functions
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    public void callRetailerScreens(View view){

        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));

    }
}