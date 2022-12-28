package com.example.pracainzynierska.projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.dataBase.DataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OldProjectsActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intentLogin = new Intent(OldProjectsActivity.this, MainActivity.class);
            startActivity(intentLogin);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_projects);


        Button buttonBackToMenu = (Button) findViewById(R.id.buttonBackToMenuClient);

        buttonBackToMenu.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewOldProjectsList);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Project> project = new ArrayList<>();


        DataBase database = new DataBase();
        database.setUserID(mAuth.getUid());

        recyclerView.setAdapter(new ProjectAdapter(project, recyclerView));

        ///////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Project> mProject = new ArrayList<Project>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("Architectural Office")
                .document(mAuth.getUid())
                .collection("Projects")
                .whereEqualTo("actualProject",false)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Project oneProject = new Project();
                                oneProject.setDocumentID(document.getId());
                                if (document.get("city") != null) {
                                    oneProject.setProjectCity(document.get("city").toString());
                                }
                                if (document.get("projectName") != null) {
                                    oneProject.setProjectName(document.get("projectName").toString());
                                }
                                if (document.get("investorName") != null) {
                                    oneProject.setProjectInvestorName(document.get("investorName").toString());
                                }
                                if (document.get("investorSurname") != null) {
                                    oneProject.setProjectInvestorSurname(document.get("investorSurname").toString());
                                }
                                if (document.get("plotNUmber") != null) {
                                    oneProject.setProjectPlotNumber("dz. nr "+document.get("plotNUmber").toString());
                                }

                                mProject.add(oneProject);

                            }
                            recyclerView.setAdapter(new ProjectAdapter(mProject, recyclerView));

                        } else {
                            Log.d("Z bazy danych", "tutaj cos nie działa?");
                        }
                    }
                });


        ///////////////////////////////////////////////////////////////////////////////////////////


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonBackToMenuClient:
                Intent intentMainMenu2 = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu2);
                this.finish();
                break;
        }
    }
}