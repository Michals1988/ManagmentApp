package com.example.pracainzynierska.clients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pracainzynierska.R;
import com.example.pracainzynierska.dataBase.DataBase;
import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ClientsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_clients);

        mAuth = FirebaseAuth.getInstance();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewClientsList);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Client> client = new ArrayList<>();


        DataBase database = new DataBase();
        database.setUserID(mAuth.getUid());

        recyclerView.setAdapter(new ClientsAdapter(client, recyclerView));

        ///////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Client> mClient = new ArrayList<Client>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        db.collection("Architectural Office")
                .document(mAuth.getUid())
                .collection("Clients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Client oneClient = new Client();
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
                            recyclerView.setAdapter(new ClientsAdapter(mClient, recyclerView));

                        } else {
                            Log.d("Z bazy danych", "tutaj cos nie działa?");
                        }
                    }
                });


        ///////////////////////////////////////////////////////////////////////////////////////////




        }

    }



