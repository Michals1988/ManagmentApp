package com.example.pracainzynierska;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.pracainzynierska.AddNewClientPage.AddNewClientActivity;
import com.example.pracainzynierska.AddNewProject.AddNewProjectActivity;
import com.example.pracainzynierska.clients.ClientsActivity;

import com.example.pracainzynierska.loginPage.LoginActivity;
import com.example.pracainzynierska.projects.ProjectsMainPageActivity;

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
        Button buttonClients = findViewById(R.id.buttonClients);
        Button buttonProjects = findViewById(R.id.buttonProjects);
        Button buttonLogout = findViewById(R.id.buttonLogOut);

        buttonNewClient.setOnClickListener(this);
        buttonNewProject.setOnClickListener(this);
        buttonClients.setOnClickListener(this);
        buttonProjects.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddClient:
                //
                Intent intentAddClientPage = new Intent(this, AddNewClientActivity.class);
                startActivity(intentAddClientPage);
                break;

            case R.id.buttonAddProject:
                Intent intentAddProject = new Intent(this, AddNewProjectActivity.class);
                startActivity(intentAddProject);
                break;

            case R.id.buttonClients:
                Intent intentClients = new Intent(this, ClientsActivity.class);
                startActivity(intentClients);
                break;

            case R.id.buttonProjects:
                Intent intentProjects = new Intent(this, ProjectsMainPageActivity.class);
                startActivity(intentProjects);
                break;

            case R.id.buttonLogOut:

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Wylogowanie")
                        .setMessage("Napewno chcesz się wylogować?")
                        .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                            FirebaseAuth.getInstance().signOut();
                            Intent intentLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intentLoginPage);
                            Toast.makeText(MainActivity.this, "Zostałeś wylogowany.", Toast.LENGTH_LONG).show();
                            MainActivity.this.finish();
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


        }

    }

}