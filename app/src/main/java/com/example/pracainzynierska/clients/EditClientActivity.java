package com.example.pracainzynierska.clients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pracainzynierska.AddNewClientPage.AddNewClientActivity;
import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.dataBase.DataBase;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditClientActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public EditText name, surname, street, houseNumber, flatNumber, zipCode, city, phoneNumber, eMail;
    private String documentID;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intentLogin = new Intent(EditClientActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        documentID = getIntent().getStringExtra("documentID");

        Button buttonDismissChengeClient = findViewById(R.id.buttonDismissChengeClient);
        Button buttonEndAddClientEdit = findViewById(R.id.buttonEndAddClientEdit);
        Button buttonBackToMenuClientEdit = findViewById(R.id.buttonBackToMenuClientEdit);

        buttonDismissChengeClient.setOnClickListener((View.OnClickListener) this);
        buttonEndAddClientEdit.setOnClickListener((View.OnClickListener) this);
        buttonBackToMenuClientEdit.setOnClickListener((View.OnClickListener) this);

        name = (EditText) findViewById(R.id.editTextNameClientEdit);
        surname = (EditText) findViewById(R.id.editTextSurnameClientEdit);
        street = (EditText) findViewById(R.id.editTextStreetClientEdit);
        houseNumber = (EditText) findViewById(R.id.editTextHouseNumberClientEdit);
        flatNumber = (EditText) findViewById(R.id.editTextFlatNumberClientEdit);
        zipCode = (EditText) findViewById(R.id.editTextZipCodeClientEdit);
        city = (EditText) findViewById(R.id.editTextCityClientEdit);
        phoneNumber = (EditText) findViewById(R.id.editTextPhoneClientEdit);
        eMail = (EditText) findViewById(R.id.editTextEmailClientEdit);

        System.out.println(documentID);

        db.collection("Architectural Office")
                .document(mAuth.getUid())
                .collection("Clients")
                .document(documentID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                if (document.get("name") != null) {
                                    name.setText(document.get("name").toString());
                                }
                                if (document.get("surname") != null) {
                                    surname.setText(document.get("surname").toString());
                                }

                                if (document.get("street") != null) {
                                    street.setText(document.get("street").toString());
                                }

                                if (document.get("houseNumber") != null) {
                                    houseNumber.setText(document.get("houseNumber").toString());
                                }

                                if (document.get("flatNumber") != null) {
                                    flatNumber.setText(document.get("flatNumber").toString());
                                }

                                if (document.get("zipCode") != null) {
                                    zipCode.setText(document.get("zipCode").toString());
                                }
                                if (document.get("city") != null) {
                                    city.setText(document.get("city").toString());
                                }

                                if (document.get("phoneNumber") != null) {
                                    phoneNumber.setText(document.get("phoneNumber").toString());
                                }
                                if (document.get("eMail") != null) {
                                    eMail.setText(document.get("eMail").toString());
                                }
                            } else {
                                //Log.d(TAG, "No such document");
                            }
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDismissChengeClient:
                finish();
                break;

            case R.id.buttonEndAddClientEdit:
                DataBase dataBase=new DataBase();
                dataBase.setUserID(mAuth.getUid().toString());
                if (name.getText().toString().equals("") ||
                        surname.getText().toString().equals("") ||
                        houseNumber.getText().toString().equals("") ||
                        city.getText().toString().equals("")) {
                    Toast.makeText(EditClientActivity.this,
                            "Wprowadź wszystkie wymagane dane!", Toast.LENGTH_LONG).show();
                } else {
                    dataBase.updateClient(
                            name.getText().toString(),
                            surname.getText().toString(),
                            street.getText().toString(),
                            houseNumber.getText().toString(),
                            flatNumber.getText().toString(),
                            zipCode.getText().toString(),
                            city.getText().toString(),
                            phoneNumber.getText().toString(),
                            eMail.getText().toString(),
                            EditClientActivity.this,
                            documentID);
                }
                Intent intentShowOneClient = new Intent(this, ShowOneClientActivity.class);
                startActivity(intentShowOneClient);
                intentShowOneClient.putExtra("documentID", documentID);
                this.finish();
                break;

            case R.id.buttonBackToMenuClientEdit:
                Intent intentMainMenu = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu);
                this.finish();
                break;
    }
}
}