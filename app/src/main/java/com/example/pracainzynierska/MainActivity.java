package com.example.pracainzynierska;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intentLoginPage = new Intent(this, LoginActivity.class);
            startActivity(intentLoginPage);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button buttonNewClient = findViewById(R.id.buttonAddClient);
        Button buttonNewProject = findViewById(R.id.buttonAddProject);

        buttonNewClient.setOnClickListener(this);
        buttonNewProject.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddClient:
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.buttonAddProject:
                Intent intentLoginPage = new Intent(this, LoginActivity.class);
                startActivity(intentLoginPage);
                break;
        }

    }

}