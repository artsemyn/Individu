package com.example.individu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<Person> personList;

    public ListAdapter(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       private TextView nameTxt;

       public MyViewHolder(final View view) {
           super(view);
           nameTxt = view.findViewById(R.id.personName);
       }
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        String name = personList.get(position).getName();
        holder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
