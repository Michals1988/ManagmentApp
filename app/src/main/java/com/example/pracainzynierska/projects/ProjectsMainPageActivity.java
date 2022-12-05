package com.example.pracainzynierska.projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;

public class ProjectsMainPageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_main_page);

        Button buttonActualProjects = findViewById(R.id.buttonActualProjects);
        Button buttonOldProjects = findViewById(R.id.buttonOldProjects);
        Button buttonBackToMenu = (Button) findViewById(R.id.buttonBackToMenuClient);

        buttonBackToMenu.setOnClickListener(this);
        buttonActualProjects.setOnClickListener(this);
        buttonOldProjects.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonActualProjects:
                Intent intentActualProjects = new Intent(this, ActualProjectsActivity.class);
                startActivity(intentActualProjects);
                break;

            case R.id.buttonOldProjects:
                Intent intentOldProjects = new Intent(this, OldProjectsActivity.class);
                startActivity(intentOldProjects);
                break;

            case R.id.buttonBackToMenuClient:
                Intent intentMainMenu2 = new Intent(this, MainActivity.class);
                startActivity(intentMainMenu2);
                this.finish();
                break;
        }
    }
}