package com.example.cityguide.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cityguide.R;

public class Retailer_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_retailer_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.login_back_button).setOnClickListener(view -> onBackPressed());


    }

    public void callForgetScreen(View view){
        Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View, String>(findViewById(R.id.login_forget_btn), "transition_forget");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Retailer_Login.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void callCreateAccount(View view){
        Intent intent = new Intent(getApplicationContext(), Retailer_SignUp.class);
        startActivity(intent);
    }

    //    Back btn Function
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}