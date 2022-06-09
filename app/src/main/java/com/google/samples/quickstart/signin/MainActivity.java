package com.google.samples.quickstart.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleBtn = findViewById(R.id.google_btn);

        Log.w(TAG, "TARINI9992 before GoogleSignInOptions.Builder");

        navigateToAuthActivity();

        /*
       String lookupScope = "https://www.googleapis.com/auth/cloud-identity.devices.lookup";


       //web client id 83485897810-jut5avvqkiqjdbjppf0b1ertnosiheic.apps.googleusercontent.com
        //android client id: 83485897810-16lgjv8bqrvtc0vn8muvkfil2rr6eds0.apps.googleusercontent.com
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(lookupScope))
                .requestIdToken("83485897810-jut5avvqkiqjdbjppf0b1ertnosiheic.apps.googleusercontent.com")
                .requestEmail()
                .build();

        //original code gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        //gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        gsc = GoogleSignIn.getClient(this, gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        */
    }

    void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                Log.w(TAG, "onActivityResult Before getting task getResult");
                task.getResult(ApiException.class);

                navigateToSecondActivity();
            } catch (ApiException e) {
                e.printStackTrace();
                Log.w(TAG, "onActivityResult exception");
            }
        }
    }

    void navigateToAuthActivity() {
        finish();
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(intent);

    }

    void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

    }
}