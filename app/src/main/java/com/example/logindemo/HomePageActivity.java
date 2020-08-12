package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private TextView txtName, txtEmail, txtAge;
    private String MAIL, USERNAME;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        txtName = findViewById(R.id.homeUser);
        txtEmail = findViewById(R.id.homeEmail);
        txtAge = findViewById(R.id.homeAge);

        preferences = PreferenceManager.getDefaultSharedPreferences(HomePageActivity.this);
        editor = preferences.edit();
        MAIL = preferences.getString("USER_MAIL", "");
        USERNAME = preferences.getString("USER_NAME", "");

        txtName.setText(USERNAME);
        txtEmail.append(MAIL);
        txtAge.append("23");

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar2);
        navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_fav:
                Toast.makeText(HomePageActivity.this, "Fav successful", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_message:
                Toast.makeText(HomePageActivity.this, "Message successful", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_logout:
                confirmLogout();
                break;
        }
        return true;
    }

    private void confirmLogout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(HomePageActivity.this);
        alert.setMessage("Confirm Logout?");
        alert.setCancelable(false);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent);
                HomePageActivity.this.finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }
}