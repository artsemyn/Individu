package com.example.individu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        TextView nameTxt = findViewById(R.id.personName);
        TextView numberTxt = findViewById(R.id.personNumber);
        TextView emailTxt = findViewById(R.id.personEmail);
        TextView addressTxt = findViewById(R.id.personAddress);

        Button callBtn = findViewById(R.id.callButton);
        Button emailBtn = findViewById(R.id.emailButton);
        Button navigateBtn = findViewById(R.id.navigateButton);
        Button shareBtn = findViewById(R.id.shareButton);
        FloatingActionButton backBtn = findViewById(R.id.backBtn);

        String name = "The name hasn't been set";
        String number = "The number hasn't been set";
        String email = "The email hasn't been set";
        String address = "The address hasn't been set";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            number = extras.getString("number");
            email = extras.getString("email");
            address = extras.getString("address");
        }
        nameTxt.setText(name);
        numberTxt.setText(number);
        emailTxt.setText(email);
        addressTxt.setText(address);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberTxt.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(callIntent);
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxt.getText().toString();
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                startActivity(mailIntent);
            }
        });

        navigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tujuan = addressTxt.getText().toString();
                Uri uri = Uri.parse("https://www.google.com/maps/dir/" + tujuan);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String finalName = name;
        String finalNumber = number;
        String finalEmail = email;
        String finalAddress = address;
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Bagikan Kontak");
                intent.putExtra(Intent.EXTRA_TEXT, "Name: " + finalName + " " + "Phone Number: " + finalNumber + " " + "Email: " + finalEmail + " " + "Address: " + finalAddress);
                startActivity(Intent.createChooser(intent, "Share via"));

            }
        });
    }
}