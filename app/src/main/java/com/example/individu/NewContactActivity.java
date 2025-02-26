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

    Button confirmBtn, cancelBtn;

    String name, number, email, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_contact);

        confirmBtn = findViewById(R.id.confirmBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        name = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        number = ((EditText) findViewById(R.id.numberInput)).getText().toString();
        email = ((EditText) findViewById(R.id.emailInput)).getText().toString();
        address = ((EditText) findViewById(R.id.adrInput)).getText().toString();

        confirmBtn.setOnClickListener(v -> {
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