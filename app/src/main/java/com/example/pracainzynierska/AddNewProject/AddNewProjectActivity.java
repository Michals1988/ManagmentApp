package com.example.pracainzynierska.AddNewProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pracainzynierska.AddNewClientPage.AddNewClientActivity;
import com.example.pracainzynierska.ForgottenPasswordActivity;
import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.dataBase.DataBase;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddNewProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText projectName, zipCode, city, street, houseNumber, flatNumber, confine, plotNumber, investorName, investorSurname;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intentLogin = new Intent(AddNewProjectActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_project);

        mAuth = FirebaseAuth.getInstance();

        Button buttonEndAddProject = (Button) findViewById(R.id.buttonEndAddProject);
        Button buttonBackToMenu = (Button) findViewById(R.id.buttonBackToMenuClient);

        buttonEndAddProject.setOnClickListener(this);
        buttonBackToMenu.setOnClickListener(this);

        projectName = findViewById(R.id.editTextProjectName);
        zipCode = findViewById(R.id.editTextZipCodeProject);
        city = findViewById(R.id.editTextCityProject);
        street = findViewById(R.id.editTextStreetProject);
        houseNumber = findViewById(R.id.editTextHouseNumberProject);
        flatNumber = findViewById(R.id.editTextFlatNumberProject);
        confine = findViewById(R.id.editTextConfineProject);
        plotNumber = findViewById(R.id.editTextPlotNumberProject);
        investorName = findViewById(R.id.editTextInvestorNameProject);
        investorSurname = findViewById(R.id.editTextInvestorSurnameProject);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonEndAddProject:
                DataBase dataBase = new DataBase();
                dataBase.setUserID(mAuth.getUid().toString());


                dataBase.addProject(1,projectName.getText().toString(),
                                    zipCode.getText().toString(),
                                    city.getText().toString(),
                                    street.getText().toString(),
                                    houseNumber.getText().toString(),
                                    flatNumber.getText().toString(),
                                    confine.getText().toString(),
                                    plotNumber.getText().toString(),
                                    investorName.getText().toString(),
                                    investorSurname.getText().toString(),
                                    AddNewProjectActivity.this);

                Intent intentMainMenu = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu);
                this.finish();

                break;

            case R.id.buttonBackToMenuClient:
                Intent intentMainMenu2 = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu2);
                this.finish();
                break;
        }
    }
}