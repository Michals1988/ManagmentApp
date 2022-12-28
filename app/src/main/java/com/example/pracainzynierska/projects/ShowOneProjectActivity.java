package com.example.pracainzynierska.projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pracainzynierska.AddNewClientPage.AddNewClientActivity;
import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.clients.Client;
import com.example.pracainzynierska.clients.ClientsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowOneProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView projectName, cityZipCode, streetNumber, confinePlotNumber;
    private String documentID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_project);

        mAuth = FirebaseAuth.getInstance();

        documentID = getIntent().getStringExtra("documentID");
        System.out.println(documentID);

        projectName = findViewById(R.id.textViewProjectNameOne);
        cityZipCode = findViewById(R.id.textViewCityZipCodeOne);
        streetNumber = findViewById(R.id.textViewStreetNumberOne);
        confinePlotNumber = findViewById(R.id.textViewConfinePlotNUmberOne);

        Button buttonEditProject = findViewById(R.id.buttonEditProject);
        Button buttonDeleteProject = findViewById(R.id.buttonDeleteProject);
        Button buttonBackToMenu = findViewById(R.id.buttonBackToMenuProject);

        buttonEditProject.setOnClickListener((View.OnClickListener) this);
        buttonDeleteProject.setOnClickListener((View.OnClickListener) this);
        buttonBackToMenu.setOnClickListener((View.OnClickListener) this);


        db.collection("Architectural Office")
                .document(mAuth.getUid())
                .collection("Projects")
                .document(documentID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                projectName.setText(document.get("projectName").toString());
                                cityZipCode.setText(document.get("zipCode").toString() + " " + document.get("city"));
                                streetNumber.setText(document.get("street") + " " + document.get("houseNumber"));
                                confinePlotNumber.setText("obr." + document.get("confine").toString() + " dz. nr " + document.get("plotNUmber"));
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
            case R.id.buttonEditProject:
                Intent intentEditProject = new Intent(this, EditProjectActivity.class);
                intentEditProject.putExtra("documentID", documentID);
                startActivity(intentEditProject);
                this.finish();
                break;

            case R.id.buttonDeleteProject:
                AlertDialog alertDialog = new AlertDialog.Builder(ShowOneProjectActivity.this)
                        .setTitle("Usuwanie")
                        .setMessage("Napewno chcesz usunąć projekt?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.collection("Architectural Office")
                                        .document(mAuth.getUid())
                                        .collection("Projects")
                                        .document(documentID)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ShowOneProjectActivity.this, "Projekt został usunięty!", Toast.LENGTH_LONG).show();

                                                Intent intentMenu = new Intent(ShowOneProjectActivity.this, MainActivity.class);
                                                startActivity(intentMenu);
                                                ShowOneProjectActivity.this.finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ShowOneProjectActivity.this, "Błąd podczas usuwania projektu.", Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;

            case R.id.buttonBackToMenuProject:
                Intent intentMainMenu = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu);
                this.finish();
                break;
        }
    }
}