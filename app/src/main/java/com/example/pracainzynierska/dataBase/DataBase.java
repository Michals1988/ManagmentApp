package com.example.pracainzynierska.dataBase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pracainzynierska.clients.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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
    String clientEmail;

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public DataBase() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void checkDataBase(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("userID", userID);


        db.collection("ArchitecturalOffice")
                .document(userID)
                .collection("Clients")
                .add(user);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addClient(String clientName, String clientSurname, String clientStreet, String clientHouseNumber,
                          String clientFlatNumber, String clientZipCode, String clientCity, String phoneNumber, String clientEmail, Context context  ) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> client = new HashMap<>();
        client.put("name", clientName);
        client.put("surname", clientSurname);
        client.put("phoneNumber", phoneNumber);
        client.put("zipCode", clientZipCode);
        client.put("city", clientCity);
        client.put("street", clientStreet);
        client.put("houseNumber", clientHouseNumber);
        client.put("flatNumber", clientFlatNumber);
        client.put("eMail", clientEmail);



        db.collection("Architectural Office")
                .document(userID)
                .collection("Clients")
                .add(client)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DataBaseInfo", "DocumentSnapShot added with ID:" + documentReference.getId());
                        Toast.makeText(context, "Klient został zapisany!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("DataBaseInfo", "DataBaseError-the client was not added");
                        Toast.makeText(context, "Błąd zapisu", Toast.LENGTH_LONG).show();
                    }
                });

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void updateClient(String clientName,
                             String clientSurname,
                             String clientStreet,
                             String clientHouseNumber,
                             String clientFlatNumber,
                             String clientZipCode,
                             String clientCity,
                             String phoneNumber,
                             String clientEmail,
                             Context context,
                             String documentID) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> client = new HashMap<>();
        client.put("name", clientName);
        client.put("surname", clientSurname);
        client.put("phoneNumber", phoneNumber);
        client.put("zipCode", clientZipCode);
        client.put("city", clientCity);
        client.put("street", clientStreet);
        client.put("houseNumber", clientHouseNumber);
        client.put("flatNumber", clientFlatNumber);
        client.put("eMail", clientEmail);


        db.collection("Architectural Office")
                .document(userID)
                .collection("Clients")
                .document(documentID)
                .update(client)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Kontakt został zaktualizowany!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Aktualizacja nie powiodła się!", Toast.LENGTH_LONG).show();
                    }
                });

    }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addProject(int projectID, String projectName, String projectZipCode, String projectCity, String projectStreet,
                           String projectHouseNumber, String projectFlatNumber, String projectConfine, String projectPlotNumber,
                           String projectInvestorName, String projectInvestorSurname, Context context) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> project = new HashMap<>();
        project.put("projectID", projectID);
        project.put("projectName", projectName);
        project.put("zipCode", projectZipCode);
        project.put("city", projectCity);
        project.put("street", projectStreet);
        project.put("houseNumber", projectHouseNumber);
        project.put("flatNumber", projectFlatNumber);
        project.put("confine", projectConfine);
        project.put("plotNUmber", projectPlotNumber);
        project.put("investorName", projectInvestorName);
        project.put("investorSurname", projectInvestorSurname);
        project.put("actualProject", true);

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
                        Toast.makeText(context, "Błąd zapisu!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public ArrayList<Client> showContacts() {


        ArrayList<Client> mClient = new ArrayList<Client>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Client oneClient = new Client();

        try {
            QuerySnapshot taskResult=Tasks.await(db.collection("Architectural Office")
                    .document(userID)
                    .collection("Clients")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    oneClient.setDocumentId(document.getId());
                                    if (document.get("name") != null) {
                                        oneClient.setClientName(document.get("name").toString());
                                    }
                                    if (document.get("surname") != null) {
                                        oneClient.setClientSurname(document.get("surname").toString());
                                    }
                                    if (document.get("phoneNumber") != null) {
                                        oneClient.setClientPhoneNumber(document.get("phoneNumber").toString());
                                    }
                                    if (document.get("street") != null) {
                                        oneClient.setClientStreet(document.get("street").toString());
                                    }
                                    if (document.get("city") != null) {
                                        oneClient.setClientCity(document.get("city").toString());
                                    }
                                    if (document.get("zipCode") != null) {
                                        oneClient.setClientZipCode(document.get("zipCode").toString());
                                    }
                                    if (document.get("houseNumber") != null) {
                                        oneClient.setClientHouseNumber(document.get("houseNumber").toString());
                                    }
                                    if (document.get("flatNumber") != null) {
                                        oneClient.setClientFlatNumber(document.get("flatNumber").toString());
                                    }
                                    mClient.add(oneClient);

                                }

                            } else {
                                Log.d("Z bazy danych", "tutaj cos nie działa?");
                            }
                        }
                    }));
        } catch (Exception e) {
        }

        return mClient;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}




