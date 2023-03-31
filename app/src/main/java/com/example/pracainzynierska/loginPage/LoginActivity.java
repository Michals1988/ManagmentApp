package com.example.pracainzynierska.loginPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pracainzynierska.ForgottenPasswordActivity;
import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.dataBase.DataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Authenticator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText textViewEmail, textViewPassword;
    String login, password;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentLogin);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        textViewEmail = (EditText) findViewById(R.id.text_login);
        textViewPassword = (EditText) findViewById(R.id.text_password);

        TextView buttonReminder = (TextView) findViewById(R.id.text_forgotPassword);
        Button buttonLogin = (Button) findViewById(R.id.button_login);

        buttonReminder.setOnClickListener((View.OnClickListener) this);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_forgotPassword:
                Intent intentReminderSite = new Intent(this, ForgottenPasswordActivity.class);
                startActivity(intentReminderSite);
                break;
            case R.id.button_login:
                login = textViewEmail.getText().toString().trim();
                password = textViewPassword.getText().toString().trim();

                if (login.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Podaj adres e-mail i hasło", Toast.LENGTH_LONG).show();
                    break;
                }
                Pattern compiledPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
                Matcher matcher = compiledPattern.matcher(login);
                if(!matcher.find()){
                    Toast.makeText(this, "Podaj prawidłowy adres e-mail.", Toast.LENGTH_LONG).show();
                    break;
                }

                Log.d("dane logowania", login + "    " + password);


                mAuth.signInWithEmailAndPassword(login, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userId=mAuth.getCurrentUser().getUid().toString();
                                    DataBase dataBase=new DataBase();
                                    dataBase.checkDataBase(userId);

                                    Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intentLogin);

                                    Toast.makeText(LoginActivity.this, "Logowanie się powiodło!", Toast.LENGTH_LONG).show();
                                    Log.d("logowanie", "udane");
                                } else {
                                    Toast.makeText(LoginActivity.this, "Logowanie NIE UDANE!", Toast.LENGTH_LONG).show();
                                    Log.d("logowanie", "NIE udane");
                                    Log.e("wrong", "onComplete: Failed=" + task.getException().getMessage());
                                }
                            }
                        });
                break;
        }

    }
}