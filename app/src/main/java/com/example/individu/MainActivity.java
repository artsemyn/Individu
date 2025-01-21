package com.example.individu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> personList;
    private RecyclerView recyclerView;
    private ListAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        personList = new ArrayList<>();

        setPersonInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListener();
        ListAdapter adapter = new ListAdapter(personList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new ListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("name", personList.get(position).getName());
                intent.putExtra("number", personList.get(position).getNumber());
                intent.putExtra("email", personList.get(position).getEmail());
                intent.putExtra("address", personList.get(position).getAddress());
                startActivity(intent);
            }
        };
    }

    private void setPersonInfo() {
        personList.add(new Person("Luke", "087741135316", "luke@email.com", "Jakarta Barat"));
        personList.add(new Person("Artem", "087741135316", "artem@email.com", "Jakarta Pusat"));
        personList.add(new Person("Vyn", "087741135316", "vyn@email.com", "Jakarta Timur"));
        personList.add(new Person("Marius", "087741135316", "marius@email.com", "Jakarta Selatan"));
    }
}