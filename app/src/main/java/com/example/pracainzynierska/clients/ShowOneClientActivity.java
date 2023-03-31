package com.example.pracainzynierska.clients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.projects.EditProjectActivity;
import com.example.pracainzynierska.projects.ShowOneProjectActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowOneClientActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonDeleteClient, buttonEditClient, buttonCallToClient, backToMenuOneClient;
    TextView nameSurnameOne, cityZipCodeClientOne, streetNumberClientOne, phoneNUmberClientOne;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String documentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_client);

        documentID = getIntent().getStringExtra("documentID");

        buttonDeleteClient = findViewById(R.id.buttonDeleteClient);
        buttonEditClient = findViewById(R.id.buttonEditClient);
        buttonCallToClient = findViewById(R.id.buttonCallToClient);
        backToMenuOneClient = findViewById(R.id.buttonBackToMenuOneClient);

        nameSurnameOne = findViewById(R.id.textViewNameSurnameOne);
        cityZipCodeClientOne = findViewById(R.id.textViewCityZipCodeClientOne);
        streetNumberClientOne = findViewById(R.id.textViewStreetNumberClientOne);
        phoneNUmberClientOne = findViewById(R.id.textViewPhoneNumberClientOne);

        buttonDeleteClient.setOnClickListener((View.OnClickListener) this);
        buttonEditClient.setOnClickListener((View.OnClickListener) this);
        buttonCallToClient.setOnClickListener((View.OnClickListener) this);
        backToMenuOneClient.setOnClickListener((View.OnClickListener) this);


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
                                nameSurnameOne.setText(document.get("name").toString() + " " + document.get("surname"));

                                String cityZipCode = new String();
                                if (document.get("zipCode") != null) {
                                    cityZipCode = document.get("zipCode").toString();
                                }
                                if (document.get("city") != null) {
                                    cityZipCode = cityZipCode + " " + document.get("city").toString();
                                }
                                cityZipCodeClientOne.setText(cityZipCode);

                                String streetNumber = new String();
                                if (document.get("street") != null) {
                                    streetNumber = document.get("street").toString();
                                }
                                if (document.get("houseNumber") != null) {
                                    streetNumber = streetNumber + document.get("houseNumber").toString();
                                }
                                if (document.get("flatNumber") != null) {
                                    streetNumber = " " + streetNumber + "/" + document.get("flatNumber").toString();
                                }
                                streetNumberClientOne.setText(streetNumber);

                                if (document.get("phoneNumber") != null) {
                                    phoneNUmberClientOne.setText(document.get("phoneNumber").toString());
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
            case R.id.buttonDeleteClient:

                new AlertDialog.Builder(ShowOneClientActivity.this)
                        .setTitle("Usuwanie")
                        .setMessage("Napewno chcesz usunąć klienta?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.collection("Architectural Office")
                                        .document(mAuth.getUid())
                                        .collection("Clients")
                                        .document(documentID)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ShowOneClientActivity.this, "Klient został usunięty!", Toast.LENGTH_LONG).show();

                                                Intent intentMenu = new Intent(ShowOneClientActivity.this, MainActivity.class);
                                                startActivity(intentMenu);
                                                ShowOneClientActivity.this.finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ShowOneClientActivity.this, "Błąd podczas usuwania klienta.", Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;

            case R.id.buttonEditClient:
                Intent intentEditClient = new Intent(ShowOneClientActivity.this, EditClientActivity.class);
                intentEditClient.putExtra("documentID", documentID);
                startActivity(intentEditClient);
                break;

            case R.id.buttonBackToMenuOneClient:
                Intent intentMainMenu = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu);
                this.finish();
                break;


            case R.id.buttonCallToClient:
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                String number = "tel:" + phoneNUmberClientOne.getText().toString();
                System.out.println(number);
                phoneIntent.setData(Uri.parse(number));

                if (ActivityCompat.checkSelfPermission(ShowOneClientActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(phoneIntent);
                break;
        }
    }
}