package com.example.pracainzynierska.projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProjectActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText projectName, zipCode, city, street, houseNumber, flatNumber, confine, plotNumber, investorName, investorSurname;
    //RadioGroup actualProject;
    RadioButton actualProject, oldProject;
    String actual;
    private String documentID;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intentLogin = new Intent(EditProjectActivity.this, MainActivity.class);
            startActivity(intentLogin);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        documentID = getIntent().getStringExtra("documentID");

        projectName = findViewById(R.id.editTextProjectNameEdit);
        zipCode = findViewById(R.id.editTextZipCodeProjectEdit);
        city = findViewById(R.id.editTextCityProjectEdit);
        street = findViewById(R.id.editTextStreetProjectEdit);
        houseNumber = findViewById(R.id.editTextHouseNumberProjectEdit);
        flatNumber = findViewById(R.id.editTextFlatNumberProjectEdit);
        confine = findViewById(R.id.editTextConfineProjectEdit);
        plotNumber = findViewById(R.id.editTextPlotNumberProjectEdit);
        investorName = findViewById(R.id.editTextInvestorNameProjectEdit);
        investorSurname = findViewById(R.id.editTextInvestorSurnameProjectEdit);

        //actualProject= findViewById(R.id.radioButton);

        actualProject=findViewById(R.id.radioButtonActualProject);
        oldProject=findViewById(R.id.radioButtonOldProject);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                                zipCode.setText(document.get("zipCode").toString());
                                city.setText(document.get("city").toString());
                                street.setText(document.get("street").toString());
                                houseNumber.setText(document.get("houseNumber").toString());
                                flatNumber.setText(document.get("flatNumber").toString());
                                confine.setText(document.get("confine").toString());
                                plotNumber.setText(document.get("plotNUmber").toString());
                                investorName.setText(document.get("investorName").toString());
                                investorSurname.setText(document.get("investorSurname").toString());
                                actual=document.get("actualProject").toString();
                                if (actual.equals("true")){
                                    actualProject.setChecked(true);
                                }
                                else {
                                    oldProject.setChecked(true);
                                }
                            } else {
                                //Log.d(TAG, "No such document");
                            }
                        }
                    }
                });

    }
}