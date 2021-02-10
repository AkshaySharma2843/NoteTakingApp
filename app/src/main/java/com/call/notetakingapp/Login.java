package com.call.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText email;
    EditText password;
    SharedPreferences sharedPreferences;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initSharedPref();
        initView();
    }


    private void initSharedPref() {
        sharedPreferences = SharedPreferencesManager.createSharedPreferences(this);
        if(!sharedPreferences.getString("NAME", "").isEmpty()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void initView() {
        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        button = findViewById(R.id.btn_registration);
        button.setOnClickListener(this);

    }

    public void saveUser(String name, String password, String email){
        if(sharedPreferences!=null){
           SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
            sharedPrefEditor.putString("NAME", name).apply();
            sharedPrefEditor.putString("EMAIL", email).apply();
            sharedPrefEditor.putString("PASSWORD", password).apply();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_registration){
            if(name.getText().toString().length() >= 5 && email.getText().toString().length() >= 5 && password.getText().toString().length()>5){
                saveUser(name.getText().toString(), email.getText().toString(), password.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Please check all the fields and write minimum of 5 letters", Toast.LENGTH_SHORT).show();
            }
        }
    }
}