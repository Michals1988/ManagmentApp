package com.example.pracainzynierska.dataBase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pracainzynierska.AddNewProject.AddNewProjectActivity;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DataBase {

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    String userID;


    int clientID;
    String clientName;
    String clientSurname;
    String clientPhoneNumber;
    String clientZipCode;
    String clientCity;
    String clientStreet;
    String clientHouseNumber;
    String clientFlatNumber;

    int projectID;
    String projectName;
    String projectZipCode;
    String projectCity;
    String projectStreet;
    String projectHouseNumber;
    String projectFlatNumber;
    String projectConfine;
    String projectPlotNumber;
    String projectInvestorName;
    String projectInvestorSurname;


    public DataBase() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }

    public void checkDataBase(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("userID", userID);


        db.collection("ArchitecturalOffice")
                .document(userID)
                .collection("Clients")
                .add(user);
    }



    public void addClient(int clientId, String clientName, String clientSurname, String phoneNumber, String clientZipCode, String clientCity, String clientStreet, String clientHouseNumber, String clientFlatNumber) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> client = new HashMap<>();
        client.put("clientID", clientId);
        client.put("name", clientName);
        client.put("surname", clientSurname);
        client.put("phoneNumber", phoneNumber);
        client.put("zipCode", clientZipCode);
        client.put("city", clientCity);
        client.put("street", clientStreet);
        client.put("houseNumber", clientHouseNumber);
        client.put("flatNumber", clientFlatNumber);




        db.collection("Architectural Office")
                .document(userID)
                .collection("Clients")
                .add(client)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DataBaseInfo", "DocumentSnapShot added with ID:" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("DataBaseInfo", "DataBaseError-the client was not added");
                    }
                });

    }


    public void addProject (int projectID, String projectName, String projectZipCode, String projectCity, String projectStreet,
                            String projectHouseNumber, String projectFlatNumber, String projectConfine, String projectPlotNumber,
                            String projectInvestorName, String projectInvestorSurname, Context context){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> project = new HashMap<>();
        project.put("projectID", projectID);
        project.put("projectName", projectName);
        project.put("zipCode", projectZipCode);
        project.put("city", projectCity);
        project.put("street", projectStreet);
        project.put("houseNumber", projectHouseNumber);
        project.put("flatNumber",projectFlatNumber);
        project.put("confine", projectConfine);
        project.put("plotNUmber", projectPlotNumber);
        project.put("investorName",projectInvestorName);
        project.put("investorSurname", projectInvestorSurname);

        db.collection("Architectural Office")
                .document(userID)
                .collection("Projects")
                .add(project)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DataBaseInfo", "DocumentSnapShot added with ID:" + documentReference.getId());
                        Toast.makeText(context, "Projekt został zapisany!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("DataBaseInfo", "DataBaseError-the project was not added");
                        Toast.makeText(context, "Błąd zapisu", Toast.LENGTH_LONG).show();
                    }
                });

    }

}


