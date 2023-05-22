package com.example.laundryreceipt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
    AlertDialog.Builder builder;

    EditText registerNama, registerNohp, registerUsername, registerNorek, registerPassword;
    Button registerButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerNama = findViewById(R.id.textnmLaundry);
        registerNorek = findViewById(R.id.textNorek);
        registerNohp = findViewById(R.id.textNoHp);
        registerUsername = findViewById(R.id.textUser);
        registerPassword = findViewById(R.id.textPassword);
        registerButton = findViewById(R.id.buttonRegistrasi);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String nama = registerNama.getText().toString();
                String norek = registerNorek.getText().toString();
                String nohp = registerNohp.getText().toString();
                String username = registerUsername.getText().toString();
                String password = registerPassword.getText().toString();
                HelperClass helperClass = new HelperClass(nama,norek, nohp, username, password);
                reference.child(username).setValue(helperClass);

               builder = new AlertDialog.Builder(Register.this);
               builder.setMessage("Data berhasil disimpan");
               builder.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int which) {
                       Intent intentRegister = new Intent(Register.this, Login.class);
                       startActivity(intentRegister);
                       finish();
                   }
               })
                       .show();

            }
        });

    }
}