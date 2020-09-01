package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextInputEditText username, user_email, user_pwd;
    public String[] workArray = {"...", "Hobbyist", "Entrepreneur", "Professional"};
    RadioGroup genderRadioGroup;
    RadioButton radioButtonChecked;
    Spinner spinnerWork;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register = findViewById(R.id.btn_app_register);
        username = findViewById(R.id.UserNameRegister);
        user_email = findViewById(R.id.UserEmailRegister);
        user_pwd = findViewById(R.id.UserPasswordRegister);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        preferences = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);
        editor = preferences.edit();
        spinnerWork = findViewById(R.id.spinnerWork);

        adapter = new ArrayAdapter(RegisterActivity.this,
                R.layout.support_simple_spinner_dropdown_item, workArray);

        spinnerWork.setAdapter(adapter);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_register.setBackground(getResources().getDrawable(R.drawable.button_color));
                btn_register.setTextColor(Color.parseColor("#FFFFFF"));
                String uname = username.getText().toString().trim();
                String useremail = user_email.getText().toString().trim();
                String userpwd = user_pwd.getText().toString().trim();

                radioButtonChecked = findViewById(genderRadioGroup.getCheckedRadioButtonId());
                String gender = radioButtonChecked.getText().toString().trim();
                String work = spinnerWork.getSelectedItem().toString();

                if (uname.isEmpty()) {
                    username.setError("Enter a valid Name");
                }
                else if (useremail.isEmpty()) {
                    user_email.setError("Enter a valid Email ID");
                }
                else if (userpwd.isEmpty()) {
                    user_pwd.setError("Enter a valid password");
                }
                else {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                    editor.putString("USER_NAME", uname);
                    editor.putString("USER_MAIL", useremail);
                    editor.putString("USER_PWD", userpwd);
                    editor.putString("USER_GENDER", gender);
                    editor.putString("USER_WORK", work);
                    editor.apply();

                    startActivity(intent);
                }
            }
        });
    }
}