package com.example.pracainzynierska.AddNewClientPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pracainzynierska.AddNewProject.AddNewProjectActivity;
import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.dataBase.DataBase;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddNewClientActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText name, surname, street, houseNumber, flatNumber, zipCode, city, phoneNumber, eMail;

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

        name = (EditText) findViewById(R.id.editTextNameClient);
        surname = (EditText) findViewById(R.id.editTextSurnameClient);
        street = (EditText) findViewById(R.id.editTextStreetClient);
        houseNumber = (EditText) findViewById(R.id.editTextHouseNumberClient);
        flatNumber = (EditText) findViewById(R.id.editTextFlatNumberClient);
        zipCode = (EditText) findViewById(R.id.editTextZipCodeClient);
        city = (EditText) findViewById(R.id.editTextCityClient);
        phoneNumber = (EditText) findViewById(R.id.editTextPhoneClient);
        eMail = (EditText) findViewById(R.id.editTextEmailClient);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonEndAddClient:
                DataBase dataBase = new DataBase();
                dataBase.setUserID(mAuth.getUid().toString());

                if (name.getText().toString().equals("") ||
                        surname.getText().toString().equals("") ||
                        houseNumber.getText().toString().equals("") ||
                        city.getText().toString().equals("")) {
                    Toast.makeText(AddNewClientActivity.this, "Wprowadź wszystkie wymagane dane!", Toast.LENGTH_LONG).show();
                } else {
                    dataBase.addClient(
                            name.getText().toString(),
                            surname.getText().toString(),
                            street.getText().toString(),
                            houseNumber.getText().toString(),
                            flatNumber.getText().toString(),
                            zipCode.getText().toString(),
                            city.getText().toString(),
                            phoneNumber.getText().toString(),
                            eMail.getText().toString(),
                            AddNewClientActivity.this);

                    Intent intentMainMenu = new Intent(this, MainActivity.class);
                    startActivity(intentMainMenu);
                    this.finish();
                }
                break;

            case R.id.buttonNextClient:

                DataBase dataBaseNextClient = new DataBase();
                dataBaseNextClient.setUserID(mAuth.getUid().toString());
                if (name.getText().toString().equals("") ||
                        surname.getText().toString().equals("") ||
                        houseNumber.getText().toString().equals("") ||
                        city.getText().toString().equals("")) {
                    Toast.makeText(AddNewClientActivity.this, "Wprowadź wszystkie wymagane dane!", Toast.LENGTH_LONG).show();
                } else {
                    dataBaseNextClient.addClient(
                            name.getText().toString(),
                            surname.getText().toString(),
                            street.getText().toString(),
                            houseNumber.getText().toString(),
                            flatNumber.getText().toString(),
                            zipCode.getText().toString(),
                            city.getText().toString(),
                            phoneNumber.getText().toString(),
                            eMail.getText().toString(),
                            AddNewClientActivity.this);

                    Intent intentAddNewClient = new Intent(this, AddNewClientActivity.class);
                    startActivity(intentAddNewClient);
                    this.finish();
                }
                break;

            case R.id.buttonBackToMenuClient:
                Intent intentMainMenu2 = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu2);
                this.finish();
                break;
        }
    }
}