package com.example.individu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> personList;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ListAdapter.RecyclerViewClickListener listener;
    private static final int REQUEST_CODE = 1;

    FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        personList = new ArrayList<>();

         addBtn = findViewById(R.id.addButton);
        addBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });


        setPersonInfo();
        setAdapter();
    }

    private void setupLongClickVibration(View view) {
        Vibrator vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(100);
            }
        }
    }

    private void setAdapter() {
        setOnClickListener();
        adapter = new ListAdapter(personList, listener);
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

            @Override
            public void onLongClick(View v, int position) {
                setupLongClickVibration(v);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete " + personList.get(position).getName() + "?")
                        .setMessage("Are you sure you want to delete this contact?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            personList.remove(position);
                            adapter.notifyItemRemoved(position);
                            setAdapter();
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        };
    }

    private void setPersonInfo() {
        personList.add(new Person("Luke", "087741135316", "luke@email.com", "Jakarta Barat"));
        personList.add(new Person("Artem", "087741135316", "artem@email.com", "Jakarta Pusat"));
        personList.add(new Person("Vyn", "087741135316", "vyn@email.com", "Jakarta Timur"));
        personList.add(new Person("Marius", "087741135316", "marius@email.com", "Jakarta Selatan"));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String name = data.getStringExtra("name");
                String number = data.getStringExtra("number");
                String email = data.getStringExtra("email");
                String address = data.getStringExtra("address");

                personList.add(new Person(name, number, email, address));
                setAdapter();
            }
        }
    }
}