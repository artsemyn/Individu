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
    private RecyclerViewClickListener listener;

    public ListAdapter(ArrayList<Person> personList, RecyclerViewClickListener listener) {
        this.personList = personList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
       private TextView nameTxt;

       public MyViewHolder(final View view) {
           super(view);
           nameTxt = view.findViewById(R.id.personName);
           view.setOnClickListener(this);
           view.setOnLongClickListener(this);
       }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }


        public boolean onLongClick(View v) {
            if (listener != null) {
                listener.onLongClick(v, getAdapterPosition());
            }
            return true; // Return true to indicate the event was handled
        }

        @Override
        public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
            return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
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

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
        void onLongClick(View v, int position);
    }
}
