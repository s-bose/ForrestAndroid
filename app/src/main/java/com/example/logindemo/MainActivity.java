package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private final String NAME = "Shiladitya Bose";
    private final String MAIL = "project@android.com";
    private final String PASSWORD = "password";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private TextInputEditText uname, pwd;
    private Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pwd = findViewById(R.id.UserPassword);
        uname = findViewById(R.id.UserEmail);
        btnLogin = findViewById(R.id.btn_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        editor = preferences.edit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnLogin.setBackground(getResources().getDrawable(R.drawable.button_color));
                btnLogin.setTextColor(Color.parseColor("#FFFFFF"));

                String mailId = uname.getText().toString().trim();
                String pass = pwd.getText().toString().trim();

                if (mailId.isEmpty()) {
                    uname.setError("Enter a valid Email Id");
                }
                else if (pass.isEmpty()) {
                    pwd.setError("Enter a valid Password");
                }
                else if (mailId.equals(MAIL) && pass.equals(PASSWORD)) {
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class
                    );
//                    intent.putExtra("Email", mailId);
//                    intent.putExtra("Name", NAME);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login done", Toast.LENGTH_LONG).show();
                    editor.putString("USER_MAIL", mailId);
                    editor.putString("USER_NAME", NAME);
                    editor.apply();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}