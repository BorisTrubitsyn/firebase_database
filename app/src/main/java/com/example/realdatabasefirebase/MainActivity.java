package com.example.realdatabasefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText name, secondName, email;
    private Button save, delete;

    private DatabaseReference myDB;
    private String USER_KEY = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClick();
    }

    private void onClick() {
        name = findViewById(R.id.Name);
        secondName = findViewById(R.id.Second);
        email = findViewById(R.id.email);

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        myDB = FirebaseDatabase.getInstance().getReference(USER_KEY);

    }

    public void onClickSave(View view) {
        String id = myDB.getKey();
        String nameUser = name.getText().toString();
        String secondNameUser = secondName.getText().toString();
        String emailUser = email.getText().toString();

        User newUser = new User(id, nameUser, secondNameUser, emailUser);

        myDB.push().setValue(newUser);
    }

    public void onClickDelete(View view) {

    }
}