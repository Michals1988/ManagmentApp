package com.example.pracainzynierska;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pracainzynierska.loginPage.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgottenPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    String email;
    TextView textResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);

        Button buttonResetPassword = findViewById(R.id.buttonResetPassword);
        Button buttonBackToLogin = findViewById(R.id.buttonBackToLogin);
        textResetPassword = findViewById(R.id.textResetPassword);

        buttonResetPassword.setOnClickListener(this);
        buttonBackToLogin.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonResetPassword:

                email = textResetPassword.getText().toString().trim();

                if (email.equals("")) {
                    Toast.makeText(this, "Podaj adres e-mail", Toast.LENGTH_LONG).show();
                    break;
                }

                Pattern compiledPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
                Matcher matcher = compiledPattern.matcher(email);
                if (!matcher.find()) {
                    Toast.makeText(this, "Podaj prawidłowy adres e-mail.", Toast.LENGTH_LONG).show();
                    break;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgottenPasswordActivity.this, "Mail został wysłany", Toast.LENGTH_LONG).show();
                                    Log.d("", "Email sent.");
                                } else {
                                    Toast.makeText(ForgottenPasswordActivity.this, "Błąd, nie istnieje konto z takim mailem", Toast.LENGTH_LONG).show();
                                    Log.d("reset", "Mail nie został wysłany");
                                }
                            }
                        });
                break;
            case R.id.buttonBackToLogin:
                Intent intentLoginPage = new Intent(this, LoginActivity.class);
                startActivity(intentLoginPage);
                this.finish();
        }

    }
}