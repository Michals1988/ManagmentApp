package com.example.pracainzynierska.AddNewClientPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddNewClientActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intentLogin = new Intent(AddNewClientActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client);

        mAuth = FirebaseAuth.getInstance();

        Button buttonEndAddClient = (Button) findViewById(R.id.buttonEndAddClient);
        Button buttonNextClient = (Button) findViewById(R.id.buttonNextClient);
        Button buttonBackToMenu = (Button) findViewById(R.id.buttonBackToMenuClient);

        buttonEndAddClient.setOnClickListener(this);
        buttonNextClient.setOnClickListener(this);
        buttonBackToMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEndAddClient:

                break;

            case R.id.buttonNextClient:                     // dodac zapisywanie aktualnie wprowadzonego klienta

                Intent intentAddNewClient = new Intent(this, AddNewClientActivity.class);
                startActivity(intentAddNewClient);
                this.finish();
                break;

            case R.id.buttonBackToMenuClient:
                Intent intentMainMenu = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu);
                this.finish();
                break;
        }
    }
}