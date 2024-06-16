package com.example.cityguide.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;

public class Retailer_SignUp extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;

    //    Get Data Variable
    TextInputLayout fullname, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_retailer_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        backBtn.setOnClickListener(view -> onBackPressed());

//        Hooks for getting data
        fullname = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

    }

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }

        Intent intent = new Intent(getApplicationContext(), Retailer_SignUp_2nd.class);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[2] = new Pair<View, String>(next, "transition_next_btn");
        pairs[3] = new Pair<View, String>(login, "transition_login_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Retailer_SignUp.this, pairs);
        startActivity(intent, options.toBundle());


    }

    //    Back btn Function
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean validateFullName() {
        String val = fullname.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullname.setError("Field can not be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No white space are allowed");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }  else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +         // at least 1 digit
                "(?=.*[a-z])" +         // at least 1 lower case letter
                "(?=.*[A-Z])" +         // at least 1 upper case letter
                "(?=.*[@#$%^&+=])" +    // at least 1 special character
                "(?!.*\\s)" +           // no white spaces
                ".{4,}" +               // at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password must be at least 4 characters long and contain at least 1 digit, 1 lower case letter, 1 upper case letter, 1 special character, and no white spaces.");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }



}