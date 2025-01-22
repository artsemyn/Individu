package com.example.individu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_contact);

        Button confirmBtn = findViewById(R.id.confirmBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);

        confirmBtn.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.nameInput)).getText().toString();
            String number = ((EditText) findViewById(R.id.numberInput)).getText().toString();
            String email = ((EditText) findViewById(R.id.emailInput)).getText().toString();
            String address = ((EditText) findViewById(R.id.adrInput)).getText().toString();

            if (name.isEmpty() || number.isEmpty() || email.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent resultItent = new Intent();
                resultItent.putExtra("name", name);
                resultItent.putExtra("number", number);
                resultItent.putExtra("email", email);
                resultItent.putExtra("address", address);
                setResult(RESULT_OK, resultItent);
                Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}