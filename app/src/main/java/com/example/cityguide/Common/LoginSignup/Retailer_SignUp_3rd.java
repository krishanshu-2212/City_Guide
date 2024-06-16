package com.example.cityguide.Common.LoginSignup;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
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
import com.hbb20.CountryCodePicker;

public class Retailer_SignUp_3rd extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;

    TextInputLayout phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_retailer_sign_up3rd);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Hooks
        backBtn = findViewById(R.id.signup_back_button3);
        next = findViewById(R.id.signup_verify_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        backBtn.setOnClickListener(view -> onBackPressed());

        phoneNumber = findViewById(R.id.signup_phone_number);
       
    }

    public void callVerifyOTPScreen(View view) {

        //Validate Field
        if (!validatePhoneNumber()){
            return;
        }//Validation succeeded and now move to next screen to verify phone number and save data

        //Get all values passed from previous screens using Intent
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim(); //Get phone number entered by user
//        String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber; //Get phone number with country code


        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[2] = new Pair<View, String>(next, "transition_verify_btn");
        pairs[3] = new Pair<View, String>(login, "transition_login_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Retailer_SignUp_3rd.this, pairs);    
        startActivity(intent, options.toBundle());

    }

    //    Back btn Function
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkPhoneNumber = "^[0-9]{10,15}$"; // Validates 10 to 15 digits without spaces

        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkPhoneNumber)) {
            phoneNumber.setError("Phone number should be between 10 to 15 digits with no white spaces!");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }



}