package com.example.pracainzynierska.projects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pracainzynierska.R;

public class ShowOneProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_project);

        String documentID=getIntent().getStringExtra("documentID");
        System.out.println(documentID);
    }
}